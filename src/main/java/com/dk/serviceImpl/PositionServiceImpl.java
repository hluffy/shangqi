package com.dk.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.omg.PortableServer.POA;
import org.springframework.stereotype.Service;

import com.dk.object.IbeaconInfo;
import com.dk.object.PieData;
import com.dk.object.PositionInfo;
import com.dk.object.PqiaData;
import com.dk.result.Result;
import com.dk.service.PositonService;
import com.dk.util.DBUtil;
import com.dk.util.myutils;
import com.mysql.fabric.xmlrpc.base.Data;
import com.sun.corba.se.spi.legacy.connection.GetEndPointInfoAgainException;

@Service
public class PositionServiceImpl implements PositonService {

	public Result getInfos() {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<PositionInfo> infos = new ArrayList<PositionInfo>();
		Connection conn = null;
		Statement st = null;

		try {
			// String sql =
			// "select * from positioning order by positioning_time desc limit 10";
			String sql = "select top 10 * from positioning order by positioning_time desc";
			String countSql = "select count(*) as sumcount from positioning";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			// ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery(countSql);
			if (rs.next()) {
				result.setCount(rs.getInt("sumcount"));
			}
			rs = st.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				PositionInfo info = new PositionInfo();
				info.setEquipmentNum(rs.getString("equipment_num"));
				info.setElec(rs.getInt("electricity"));
				info.setLog(rs.getDouble("longitude"));
				info.setLat(rs.getDouble("latitude"));
				info.setPositionMode(rs.getString("positioning_mode"));
				info.setArea(rs.getString("area"));
				// info.setPositionTime(rs.getTimestamp("positioning_time"));

				Timestamp positionTime = rs.getTimestamp("positioning_time");
				info.setPositionTime(positionTime);
				if (positionTime != null) {
					info.setPositionTimeStr(sdf.format((Date) positionTime));
				} else {
					info.setPositionTimeStr("");
				}

				infos.add(info);
			}

			result.setData(infos);
			result.setMessage("操作成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("操作失败");
			result.setStates(false);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	// 根据车架号查询设备位置信息
	// @Override
	// public Result getInfoAsFrameNum(String frameNum) {
	// // TODO Auto-generated method stub
	// Result result = new Result();
	// Connection conn = null;
	// PreparedStatement ps = null;
	// PositionInfo info = new PositionInfo();
	//
	// try {
	// String sql =
	// "select top 1 * from positioning where equipment_num = (select equipment_num from binding where frame_num=? and bing_type=?) order by positioning_time desc";
	// conn = DBUtil.getConnection();
	// ps = conn.prepareStatement(sql);
	// ps.setString(1, frameNum);
	// ps.setString(2, "绑定");
	//
	// ResultSet rs = ps.executeQuery();
	// if(rs.next()){
	// info.setEquipmentNum(rs.getString("equipment_num"));
	// info.setElec(rs.getInt("electricity"));
	// info.setLog(rs.getDouble("longitude"));
	// info.setLat(rs.getDouble("latitude"));
	// info.setPositionMode(rs.getString("positioning_mode"));
	// info.setPositionTime(rs.getTimestamp("positioning_time"));
	// info.setArea(rs.getString("area"));
	// info.setFrameNum(frameNum);
	// }else{
	// info=null;
	// }
	//
	// result.setData(info);
	// result.setMessage("查询成功");
	// result.setStates(true);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// result.setMessage("查询失败");
	// result.setStates(false);
	// e.printStackTrace();
	// } finally{
	// if(conn!=null){
	// try {
	// conn.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// if(ps!=null){
	// try {
	// ps.close();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	// return result;
	// }
	// 综合查询数据库信息。
	@Override
	public Result getInfoAsFrameNum(PqiaData info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		Statement st = null;
		List<PositionInfo> infos = new ArrayList<PositionInfo>();
		// 传过来可能是三个条件
		try {
			// StringBuffer sql=new
			// StringBuffer("select  a.longitude,a.EQUIPMENT_NUM from positioning a where 1=1;");
			// StringBuffer sql1=new
			// StringBuffer("select b.EQUIPMENT_NUM  from binding  b where 1=1;");
			// 需要拼接的语句
			StringBuffer sql = new StringBuffer(
					"select LONGITUDE,LATITUDE,AREA,b.FRAME_NUM from POSITIONING a,BINDING b  where a.equipment_num in( select equipment_num from BINDING where frame_num in(select vin from pqiadata where 1=1");

			System.out.println(info.getFrameNum());
			// if (info.getSingleframe()==0) {
			// sql.append("and shift='"+info.getFrameNum()+"'))");
			// }else
			// 设备号
			if (info.getSingleframe() == 0) {
				sql.append("and frame_num like '%" + info.getFrameNum()
						+ "%'))");
			} else if (info.getSingleframe() == 1) {
				// 中文描述
				sql.append("and defect_mode_name like '%" + info.getFrameNum()
						+ "%'))");
			} else {
				sql.append("and 1=2))");
			}
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				PositionInfo position = new PositionInfo();
				position.setLog(rs.getDouble("LONGITUDE"));
				position.setLat(rs.getDouble("LATITUDE"));
				position.setArea(rs.getString("AREA"));
				position.setFrameNum(rs.getString("FRAME_NUM"));
				infos.add(position);
			}
			// if (info.getSingleframe()==0) {
			// // for (PositionInfo positionInfo : infos) {
			// String
			// sqlname="select min(vin) from pqiadata where vin in (select frame_num from BINDING where equipment_num in (select equipment_num from POSITIONING where LONGITUDE='"+position.getLog()+"'and LATITUDE='"+position.getLat()+"'))";
			// conn = DBUtil.getConnection();
			// ps = conn.prepareStatement(sqlname.toString());
			// ResultSet rs1 = ps.executeQuery();
			// if (rs1.next()) {
			// position.setFrameNum(rs1.getString("vin"));
			// }
			// // }
			// }else if (info.getSingleframe()==1) {
			// // for (PositionInfo positionInfo : infos) {
			// String
			// sqlname1="select min(defect_mode_name) from pqiadata where vin in (select frame_num from BINDING where equipment_num in (select equipment_num from POSITIONING where LONGITUDE='"+position.getLog()+"'and LATITUDE='"+position.getLat()+"'))";
			// conn = DBUtil.getConnection();
			// ps = conn.prepareStatement(sqlname1.toString());
			// ResultSet rs2 = ps.executeQuery();
			// if (rs2.next()) {
			// position.setFrameNum(rs2.getString("DEFECT_DEMO_NAME"));
			// // }
			// }
			// }
			// infos.add(position);
			// }

			System.out.println(infos);
			result.setData(infos);
			result.setMessage("查询成功");
			result.setStates(true);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("查询失败");
			result.setStates(false);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public Result getInfo(PositionInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<PositionInfo> infos = new ArrayList<PositionInfo>();

		try {
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			// StringBuffer sql = new
			// StringBuffer("select * from positioning where 1= 1");
			StringBuffer sql = new StringBuffer(
					"select top 10 id,* from positioning where 1= 1");
			StringBuffer countsql = new StringBuffer(
					"select count(*) as sumcount from positioning where 1= 1");
			StringBuffer ssql = new StringBuffer();


			if (info.getEquipmentNum() != null
					&& !info.getEquipmentNum().isEmpty()) {
				sql.append(" and equipment_num like '%" + info.getEquipmentNum()
						+ "%'");
				countsql.append(" and equipment_num like '%" + info.getEquipmentNum()
						+ "%'");
				ssql.append(" and equipment_num like '%" + info.getEquipmentNum()
						+ "%'");
			}
			if(info.getEquipmentNum()!=null&&!info.getEquipmentNum().isEmpty()){
				sql.append(" and equipment_num like '%"+info.getEquipmentNum()+"'"); 
				countsql.append(" and equipment_num like '%"+info.getEquipmentNum()+"'");
				ssql.append(" and equipment_num like '%"+info.getEquipmentNum()+"'");

			}
			if (info.getElec() != null) {
				sql.append(" and electricity=" + info.getElec());
				countsql.append(" and electricity=" + info.getElec());
				ssql.append(" and electricity=" + info.getElec());
			}
			if (info.getPositionMode() != null
					&& !info.getPositionMode().isEmpty()) {
				sql.append(" and positioning_mode='" + info.getPositionMode()
						+ "'");
				countsql.append(" and positioning_mode='"
						+ info.getPositionMode() + "'");
				ssql.append(" and positioning_mode='" + info.getPositionMode()
						+ "'");
			}
			if (info.getArea() != null && !info.getArea().isEmpty()) {
				sql.append(" and area = '" + info.getArea() + "'");
				countsql.append(" and area = '" + info.getArea() + "'");
				ssql.append(" and area = '" + info.getArea() + "'");
			}
			// if(info.getPage()!=0&&info.getTotal()!=0){
			// sql.append(" limit "+(info.getPage()-1)*info.getTotal()+","+info.getTotal());
			// }

			// sql.append(" limit "+info.getPage()*10+", 10");
			sql.append(" and id not in(select top " + info.getPage() * 10
					+ " id from positioning where 1 = 1 " + ssql.toString()
					+ " order by positioning_time desc)");
			sql.append(" order by positioning_time desc");

			ResultSet rs = st.executeQuery(countsql.toString());
			if (rs.next()) {
				result.setCount(rs.getInt("sumcount"));
			}

			rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while (rs.next()) {
				PositionInfo position = new PositionInfo();
				position.setEquipmentNum(rs.getString("equipment_num"));
				position.setElec(rs.getInt("electricity"));
				position.setLog(rs.getDouble("longitude"));
				position.setLat(rs.getDouble("latitude"));
				position.setPositionMode(rs.getString("positioning_mode"));
				position.setArea(rs.getString("area"));
				Timestamp positionTime = rs.getTimestamp("positioning_time");
				position.setPositionTime(positionTime);
				if (positionTime != null) {
					position.setPositionTimeStr(sdf.format((Date) positionTime));
				}

				infos.add(position);
			}
			result.setData(infos);
			result.setMessage("查询成功");
			result.setStates(true);
			} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("查询失败");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public Result getInfoForPie() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<PieData> infos = new ArrayList<PieData>();

		try {
			String sql = "select count(*) as value,positioning_mode from positioning group by positioning_mode";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PieData info = new PieData();
				info.setValue(rs.getInt("value"));
				info.setName(rs.getString("positioning_mode"));
				infos.add(info);
			}

			result.setData(infos);
			result.setMessage("查询成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("查询失败");
			result.setStates(false);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public Result addInfo(PositionInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if (info.getEquipmentNum() == null || info.getEquipmentNum().isEmpty()) {
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into positioning(equipment_num,electricity,longitude,latitude,positioning_mode,positioning_time,area) values(?,?,?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getEquipmentNum());
			ps.setInt(2, info.getElec());
			ps.setDouble(3, info.getLog());
			ps.setDouble(4, info.getLat());
			ps.setString(5, info.getPositionMode());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.setString(7, info.getArea());

			ps.execute();

			result.setStates(true);
			result.setMessage("插入成功");
			result.setData(info);
			result.setId(info.getEquipmentNum());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("插入失败");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public Result updateInfo(PositionInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if (info.getEquipmentNum() == null || info.getEquipmentNum().isEmpty()) {
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "update positioning set electricity=?,longitude=?,latitude=?,positioning_mode=?,positioning_time=?,area=? where equipment_num = ?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, info.getElec());
			ps.setDouble(2, info.getLog());
			ps.setDouble(3, info.getLat());
			ps.setString(4, info.getPositionMode());
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			ps.setString(6, info.getArea());
			ps.setString(7, info.getEquipmentNum());

			ps.execute();

			result.setStates(true);
			result.setMessage("更新成功");
			result.setData(info);
			result.setId(info.getEquipmentNum());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("更新失败");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public Result deleteInfo(PositionInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if (info.getEquipmentNum() == null || info.getEquipmentNum().isEmpty()) {
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from positioning from where equipment_num=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getEquipmentNum());

			ps.execute();

			result.setStates(true);
			result.setMessage("删除成功");
			result.setData(info);
			result.setId(info.getEquipmentNum());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("删除失败");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	// 低电量饼图
	@Override
	public Result getInfoLow() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		int count = 0;
		int lowcount = 0;
		List<PieData> infos = new ArrayList<PieData>();
		try {
			String sql = "select count(distinct(equipment_num)) as sumcount from positioning";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt("sumcount");
			}
			sql = "select count(distinct(equipment_num)) as lowcount from positioning where electricity <= 20";
			rs = st.executeQuery(sql);
			if (rs.next()) {
				PieData info = new PieData();
				lowcount = rs.getInt("lowcount");
				info.setName("需要充电的设备");
				info.setValue(lowcount);
				infos.add(info);
			}
			if (count - lowcount > 0) {
				PieData info = new PieData();
				info.setName("正常设备");
				info.setValue(count - lowcount);
				infos.add(info);
			}
			result.setStates(true);
			result.setData(infos);
			result.setMessage("查询成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("查询失败");
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}


	@Override
	public Result getifdamagedcardata(String theinputdata) {
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		IbeaconInfo info = new IbeaconInfo();
		try {
			// String sql =
			// "select top 1 * from positioning where equipment_num = (select equipment_num from binding where frame_num=? and bing_type=?) order by positioning_time desc";
			String sql = "select LONGITUDE,LATITUDE from POSITIONING where equipment_num in( select equipment_num from BINDING where frame_num in (select vin from pqiadata where custom_name_en=?))";
			conn = DBUtil.getConnection();
			// ps = conn.prepareStatement();
			// ResultSet rs = ps.executeQuery(sql);
			// ps.setString(1,CUSTOM_NAME_CN);
			// ps = conn.prepareStatement(sql);
			// ps = conn.createStatement();
			// ps.setString(1,theinputdata);
			// ResultSet rs = ps.executeQuery(sql);
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, theinputdata);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// info.setFrameNum(theinputdata);
				info.setLog(rs.getDouble("LONGITUDE"));
				info.setLat(rs.getDouble("LATITUDE"));
			} else {
				info = null;
			}
			// if(info==null)
			// info=null;
			// }
			System.out.println(info.getLat());
			System.out.println(info.getLog());
			result.setData(info);
			result.setMessage("查询成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("查询失败");
			result.setStates(false);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	// 通过设备号来查询经纬度
	@Override
	public Result getDeviceNumber(PqiaData info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		Statement st1= null;
		Statement st2 = null;
		List<PositionInfo> infos = new ArrayList<PositionInfo>();
		try {
//			String sql = "select top 1 LONGITUDE,LATITUDE,AREA,EQUIPMENT_NUM from positioning where POSITIONING_TIME in (select MAX(POSITIONING_TIME) from positioning where equipment_num like '%"
//					+ info.getDevicenumber() + "%') order by POSITIONING_TIME desc";
//			System.out.println(sql.toString());
//			conn = DBUtil.getConnection();
//			// ps = conn.prepareStatement(sql.toString());
//			Statement st = conn.createStatement();
//			// ps.setString(1,info.getFrameNum());
//			ResultSet rs = st.executeQuery(sql.toString());
//			if (rs.next()) {
//				PositionInfo position = new PositionInfo();
//				position.setLog(rs.getDouble("longitude"));
//				position.setLat(rs.getDouble("LATITUDE"));
//				position.setArea(rs.getString("AREA"));
//				position.setFrameNum(rs.getString("EQUIPMENT_NUM"));
//				infos.add(position);
//			}
			//根据设备号取经纬度的语句
			StringBuffer sql1=new StringBuffer("select top 1 AREA,equipment_num,longitude,latitude,POSITIONING_TIME from positioning where 1=1 ");
				sql1.append("and equipment_num like'%"+info.getDevicenumber()+"%'");
				sql1.append(" order by POSITIONING_TIME desc");
		    conn = DBUtil.getConnection();
			st1=conn.createStatement();
			ResultSet rs1 = st1.executeQuery(sql1.toString());
			System.out.println(sql1.toString());
			PositionInfo position1 = new PositionInfo();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (rs1.next()) {
				position1.setLog(rs1.getDouble("LONGITUDE"));
				position1.setLat(rs1.getDouble("LATITUDE"));
				position1.setArea(rs1.getString("AREA"));
				position1.setFrameNum(rs1.getString("equipment_num"));
				Timestamp timestamp = rs1.getTimestamp("POSITIONING_TIME");
				if (timestamp!=null) {
					position1.setResultpositionTime(sdf.format((Date)timestamp));
				}
				infos.add(position1);
			}
			StringBuffer sql2=new StringBuffer("select top 1  vin,CUSTOM_NAME_CN,CREATE_USER,REPAIR_USER from pqiadatacar a where  vin in(select FRAME_NUM from BINDING where EQUIPMENT_NUM ='"+position1.getFrameNum()+"') order by CREATE_TIME");
			st2=conn.createStatement();
			ResultSet rs2 = st2.executeQuery(sql2.toString());
			System.out.println(sql2);
			if(rs2.next()){
				position1.setChinesedescription(rs2.getString("CUSTOM_NAME_CN"));
				position1.setPersonliable(rs2.getString("CREATE_USER"));
				position1.setRepairman(rs2.getString("REPAIR_USER"));
				position1.setVin(rs2.getString("vin"));
				infos.add(position1);
			}
			result.setData(infos);
			result.setMessage("查询成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("查询失败");
			result.setStates(false);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	// 这是 根据条件查询所有数据
	@Override
	public Result getInfoAsFrameNum1(PqiaData info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		List<PositionInfo> infos = new ArrayList<PositionInfo>();
		try {
			StringBuffer sql = new StringBuffer(
					"select LONGITUDE,LATITUDE,AREA, distinct (equipment_num) from POSITIONING where equipment_num in( select equipment_num from BINDING where frame_num in(select vin from pqiadatacar where 1=1");
			if (info.getVinnumber()!=null && !info.getVinnumber().isEmpty()) {
				sql.append(" and vin like'%"+info.getVinnumber()+"%'");
			}
			
			//为空是or不为空是and
			if (info.getVehicledefects() ==null) {
				sql.append(" or ");
			}else{
				sql.append(" and ");
			}
			sql.append(" ( DR_FLAG='"+ info.getVehicledefects()
					+"' or DEFECT_STATUS='"+info.getVehicledefects()
					+"' or CUSTOM_NAME_CN like '%"+info.getVehicledefects()
					+"%' or CUSTOM_NAME_EN like '%"+info.getVehicledefects()
					+"%' or PART_NAME_FIRST ='"+info.getVehicledefects()
					+"' or PART_NAME_SECONT='"+info.getVehicledefects()
					+"' or PART_NAME_THIRD='"+info.getVehicledefects()+"')");
//			sql.append("  ( shift='" + info.getVehicledefects()
//					+ "' or defect_status like '" + info.getVehicledefects()
//					+ "' or shift='" + info.getVehicledefects() + "'or shift='"
//					+ info.getVehicledefects() + "'or shift='"
//					+ info.getVehicledefects() + "'or defect_status='"
//					+ info.getVehicledefects() + "'or dr_flag='"
//					+ info.getVehicledefects() + "'or difference='"
//					+ info.getVehicledefects() + "'or defect_level='"
//					+ info.getVehicledefects() + "'or create_user='"
//					+ info.getVehicledefects() + "'or vin='"
//					+ info.getVehicledefects() + "'or tps='"
//					+ info.getVehicledefects() + "'or series='"
//					+ info.getVehicledefects() + "'or material='"
//					+ info.getVehicledefects() + "'or csn_body='"
//					+ info.getVehicledefects() + "'or defect_mode_name='"
//					+ info.getVehicledefects() + "'or defect_type='"
//					+ info.getVehicledefects() + "'or part_name_third='"
//					+ info.getVehicledefects() + "'or custom_name_cn='"
//					+ info.getVehicledefects() + "'or custom_name_en='"
//					+ info.getVehicledefects() + "'or duty_dept='"
//					+ info.getVehicledefects() + "'or confirm_user='"
//					+ info.getVehicledefects() + "'or repair_user='"
//					+ info.getVehicledefects() + "' )");
			if (info.getNewspaperdefect() ==null ) {
				sql.append(" or ");
			}else{
				sql.append(" and ");
			}
			sql.append("  (CREATE_USER ='"+info.getNewspaperdefect()
					+"' or SHIFT_GROUP='"+info.getNewspaperdefect()
					+"' or REPAIR_LINE_NAME='"+info.getNewspaperdefect()
					+"' or (DUTY_USER=(select employee_name  from employee where employee_id='"+info.getNewspaperdefect()+"')"
							+ "and SHIFT_GROUP=(select employee_shift_group from employee where employee_id='"+info.getNewspaperdefect()+"'))"
					+ "or SHIFT='"+info.getNewspaperdefect()+"')");
//			sql.append("  ( shift='" + info.getNewspaperdefect()
//					+ "' or defect_status like '" + info.getNewspaperdefect()
//					+ "' or shift='" + info.getNewspaperdefect() + "'or shift='"
//					+ info.getNewspaperdefect() + "'or shift='"
//					+ info.getNewspaperdefect() + "'or defect_status='"
//					+ info.getNewspaperdefect() + "'or dr_flag='"
//					+ info.getNewspaperdefect() + "'or difference='"
//					+ info.getNewspaperdefect() + "'or defect_level='"
//					+ info.getNewspaperdefect() + "'or create_user='"
//					+ info.getNewspaperdefect() + "'or vin='"
//					+ info.getNewspaperdefect() + "'or tps='"
//					+ info.getNewspaperdefect() + "'or series='"
//					+ info.getNewspaperdefect()+ "'or material='"
//					+ info.getNewspaperdefect() + "'or csn_body='"
//					+ info.getNewspaperdefect() + "'or defect_mode_name='"
//					+ info.getNewspaperdefect() + "'or defect_type='"
//					+ info.getNewspaperdefect() + "'or part_name_third='"
//					+ info.getNewspaperdefect() + "'or custom_name_cn='"
//					+ info.getNewspaperdefect() + "'or custom_name_en='"
//					+ info.getNewspaperdefect() + "'or duty_dept='"
//					+ info.getNewspaperdefect() + "'or confirm_user='"
//					+ info.getNewspaperdefect() + "'or repair_user='"
//					+ info.getNewspaperdefect() + "' )");
			if (info.getRepairman() ==null) {
				sql.append(" or ");
			}else{
				sql.append(" and ");
			}
			//条件有返修人，返修工号，责任部门，车型号，责任部门
			sql.append(" (REPAIR_USER='"+info.getRepairman()
					+"' or (DUTY_USER=(select employee_name  from employee where employee_id='"+info.getRepairman()+"')"
							+ "and SHIFT_GROUP=(select employee_shift_group from employee where employee_id='"+info.getRepairman()+"'))"
									+ " or REPAIR_LINE_NAME='"+info.getRepairman()+"' "
											+ "or SERIES='"+info.getRepairman()+"'"
													+ "or DUTY_DEPT='"+info.getRepairman()+"')");
//			sql.append("  ( shift='" + info.getRepairman()
//					+ "' or defect_status like '" + info.getRepairman()
//					+ "' or shift='" + info.getRepairman() + "'or shift='"
//					+ info.getRepairman() + "'or shift='"
//					+ info.getRepairman() + "'or defect_status='"
//					+ info.getRepairman() + "'or dr_flag='"
//					+ info.getRepairman() + "'or difference='"
//					+ info.getRepairman() + "'or defect_level='"
//					+ info.getRepairman() + "'or create_user='"
//					+ info.getRepairman() + "'or vin='"
//					+ info.getRepairman() + "'or tps='"
//					+ info.getRepairman() + "'or series='"
//					+ info.getRepairman() + "'or material='"
//					+ info.getRepairman() + "'or csn_body='"
//					+ info.getRepairman() + "'or defect_mode_name='"
//					+ info.getRepairman() + "'or defect_type='"
//					+ info.getRepairman() + "'or part_name_third='"
//					+ info.getRepairman() + "'or custom_name_cn='"
//					+ info.getRepairman() + "'or custom_name_en='"
//					+ info.getRepairman() + "'or duty_dept='"
//					+ info.getRepairman() + "'or confirm_user='"
//					+ info.getRepairman() + "'or repair_user='"
//					+ info.getRepairman() + "' )");
			if (info.getStatittime() != null && info.getStatittime()!=null && info.getEndtime() != null && info.getEndtime()!=null && info.getThistime()!=null && info.getThistime().isEmpty()) {
				String str=info.getThistime();
				DateTime da=DateTime.parse(str);
				sql.append(")) order by POSITIONING_TIME");
//				sql.append(" and '" + info.getEndtime() + "'>'"+info.getThistime()+"'>'"
//						+ info.getStatittime() + "'))");
			} else {
				sql.append(")) order by POSITIONING_TIME");
			}

			System.out.println(sql.toString());
			conn = DBUtil.getConnection();
			// ps = conn.prepareStatement(sql.toString());
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			// ResultSet rs = ps.executeQuery();
			List<PositionInfo> list=new ArrayList<PositionInfo>();
			while (rs.next()) {
				
				PositionInfo position = new PositionInfo();
				PositionInfo position1 = new PositionInfo();
				position.setLog(rs.getDouble("LONGITUDE"));
				position.setLat(rs.getDouble("LATITUDE"));
				position.setArea(rs.getString("AREA"));
				position1.setEquipmentNum(rs.getString("equipment_num"));
				infos.add(position);
				list.add(position1);
			}	
			System.out.println("执行完毕");
			for (PositionInfo positionInfo : list) {
				String sql2 = "select FRAME_NUM from BINDING where EQUIPMENT_NUM in ('"
						+ positionInfo.getEquipmentNum() + "')";
				System.out.println("sql2:" + sql2.toString());
				ResultSet rs2 = st.executeQuery(sql2.toString());
				while (rs2.next()) {
					PositionInfo position = new PositionInfo();
					position.setFrameNum(rs2.getString("FRAME_NUM"));
					infos.add(position);
				}
			}
				

			result.setData(infos);
			result.setMessage("查询成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("查询失败");
			result.setStates(false);
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	
	/** 
	* @Title: getInfoAsFrameNum2 
	* @Description: TODO(实现思路，根据用户传入的参数进行匹配查询。页面要) 
	* @param @param info
	* @param @return 设定文件 
	* @return Result 返回类型 
	* @throws 
	*/ 
	@Override
	public Result getInfoAsFrameNum2(PqiaData info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		List<PositionInfo> infos = new ArrayList<PositionInfo>();
		try {
			StringBuffer sql = new StringBuffer(
					"select equipment_num from BINDING where frame_num in(select vin from pqiadatacar where 1=1");
//			StringBuffer sql1=new StringBuffer("select top 1 distinct(equipment_num),LONGITUDE,LATITUDE,AREA,positioning_time from POSITIONING where equipment_num =");
			if (myutils.mystring(info.getVinnumber())) {
				sql.append(" and vin like'%"+info.getVinnumber()+"%'");
			}
			if ((myutils.mystring(info.getDefectpeople()) && myutils.mystring(info.getNewspaperdefect()))) {
				sql.append(" and CREATE_USER in (select "+info.getDefectpeople()+" form  "+info.getDefectpeople()+"like '%" +info.getNewspaperdefect()+"%')" );
			}
			if ((myutils.mystring(info.getDefectcar()) && myutils.mystring(info.getVehicledefects()))) {
				sql.append(" and CREATE_USER in (select "+info.getDefectcar()+" form  "+info.getDefectcar()+"like '%" +info.getVehicledefects()+"%')");
			}
			if (myutils.mystring(info.getRepairpeople())&& myutils.mystring(info.getRepairman())) {
				sql.append(" and CREATE_USER in (select "+info.getRepairpeople()+" form  "+info.getRepairpeople()+"like '%" +info.getRepairman()+"%')");
			}
			if ((myutils.mystring(info.getThistime()) && info.getEndtime()!=null && info.getStatittime()!=null)) {
//				DateTime info.getEndtime();
//				Timestamp
				sql.append("and '"+info.getThistime()+"' = '"+info.getStatittime()+"'>"+info.getEndtime()+"'");
			}
			sql.append(")) order by POSITIONING_TIME");
			System.out.println(sql.toString());
			conn = DBUtil.getConnection();
			// ps = conn.prepareStatement(sql.toString());
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			// ResultSet rs = ps.executeQuery();
			List<PositionInfo> list=new ArrayList<PositionInfo>();
			while (rs.next()) {
				
				PositionInfo position = new PositionInfo();
				PositionInfo position1 = new PositionInfo();
				position.setLog(rs.getDouble("LONGITUDE"));
				position.setLat(rs.getDouble("LATITUDE"));
				position.setArea(rs.getString("AREA"));
				position1.setEquipmentNum(rs.getString("equipment_num"));
				infos.add(position);
				list.add(position1);
			}	
			System.out.println("执行完毕");
//			for (PositionInfo positionInfo : list) {
//				String sql2 = "select FRAME_NUM from BINDING where EQUIPMENT_NUM in ('"
//						+ positionInfo.getEquipmentNum() + "')";
//				System.out.println("sql2:" + sql2.toString());
//				ResultSet rs2 = st.executeQuery(sql2.toString());
//				while (rs2.next()) {
//					PositionInfo position = new PositionInfo();
//					position.setFrameNum(rs2.getString("FRAME_NUM"));
//					for (PositionInfo positionInfo2 : infos) {
//						if (positionInfo2.) {
//							
//						infos.add(position);
//						}
//					}
//					
//				}
//			}
				sql.append(")) order by POSITIONING_TIME");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result.setMessage("查询失败");
				result.setStates(false);
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return result;
		}
  
	
	/** 
	* @Title: getInfoAsFrameNum3 
	* @Description: TODO(查询第三版本) 
	* @param @param info
	* @param @return 设定文件 
	* @return Result 返回类型 
	* @throws 
	*/ 
	@Override
	public Result getInfoAsFrameNum3(PqiaData info) {
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		Statement st2=null;
		Statement st1=null;
		List<PositionInfo> infos = new ArrayList<PositionInfo>();
		Set<PositionInfo> set=new HashSet<PositionInfo>();
		try {
			
			//查出多条设备号的sql语句
			StringBuffer sql = new StringBuffer(
					"select equipment_num from BINDING where frame_num in(select vin from pqiadatacar  where 1=1 ");
						if (myutils.mystring(info.getVinnumber())) {
				sql.append(" and vin like'%"+info.getVinnumber()+"%'");
			}
			//报缺陷人条件选择
			if ((myutils.mystring(info.getDefectpeople()) && myutils.mystring(info.getNewspaperdefect()))) {
				System.out.println(info.getDefectpeople());
				if (info.getDefectpeople().equals("CREATE_USER")) {
					sql.append("and CREATE_USER like'%"+info.getNewspaperdefect()+"%'");
				}else{
				sql.append(" and CREATE_USER in (select employee_name from employee where "+info.getDefectpeople()+" like '%"
						+ "" +info.getNewspaperdefect()+"%')" );
				}}
			//缺陷类型查询
			if ((myutils.mystring(info.getDefectcar()) && myutils.mystring(info.getVehicledefects()))) {
//				sql.append(" and CREATE_USER in (select "+info.getDefectcar()+" form  employee where"+info.getDefectcar()+"= '" +info.getVehicledefects()+"')");
				sql.append("and "+info.getDefectcar()+" like '%"+info.getVehicledefects()+"%'");
			}
			//返修人条件选择
			if (myutils.mystring(info.getRepairpeople())&& myutils.mystring(info.getRepairman())) {
				System.out.println(info.getRepairpeople());
				if (info.getRepairpeople().equals("REPAIR_USER")) {
					sql.append(" and REPAIR_USER like'%"+info.getRepairman()+"%'");
				}else{
				sql.append(" and REPAIR_USER in (select employee_name from  employee where "+info.getRepairpeople()+" like '%" +info.getRepairman()+"%')");
				}
				}
			//时间
			if ((myutils.mystring(info.getThistime()) && info.getEndtime()!=null && info.getStatittime()!=null)) {
//				DateTime info.getEndtime();
//				Timestamp
				sql.append("and ("+info.getThistime()+" > '"+info.getStatittime()+"' and "+info.getThistime()+" <'"+info.getEndtime()+"')");
			}
			sql.append(") and BING_TYPE='绑定'");
			System.out.println(sql.toString());
			conn = DBUtil.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			List<PositionInfo> list=new ArrayList<PositionInfo>();
			while (rs.next()) {
				PositionInfo position = new PositionInfo();
				position.setFrameunmber(rs.getString("equipment_num"));
//				set.add(position);
				list.add(position);
			}	
			
			//set查出数据
//			Iterator<PositionInfo> it = set.iterator();
//			while(it.hasNext()){//判断是否有下一个
//			it.next();//取出元素
//			}
			//list
			
			///////////////////////////////////////////
			for (PositionInfo positionInfo : list) {
				String frameNumbr = positionInfo.getFrameunmber();
//		    	//and equipment_num ='255.255.201.252'  order by POSITIONING_TIME desc
//		    	sql1.append("and equipment_num ='"+it.next()+"' order by POSITIONING_TIME desc");
//			}
			//根据设备号取经纬度的语句
			StringBuffer sql1=new StringBuffer("select top 1 AREA,equipment_num,longitude,latitude,POSITIONING_TIME from positioning where 1=1 ");
//			if (it.next()!=null) {
				sql1.append("and equipment_num ='"+frameNumbr+"'");
//			}else{
				sql1.append(" order by POSITIONING_TIME desc");
				
//			}
//		    sql1.append("and equipment_num ='"+it.next().getFrameunmber()+"'");
//		    if (it.next()==null) {
//			}else{
			    
//			}
		    conn = DBUtil.getConnection();
			st1=conn.createStatement();
			ResultSet rs1 = st1.executeQuery(sql1.toString());
			System.out.println(sql1.toString());
			PositionInfo position1 = new PositionInfo();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if (rs1.next()) {
				position1.setLog(rs1.getDouble("LONGITUDE"));
				position1.setLat(rs1.getDouble("LATITUDE"));
				position1.setArea(rs1.getString("AREA"));
				position1.setFrameNum(rs1.getString("equipment_num"));
//				position1.setResultpositionTime(rs1.getString("POSITIONING_TIME"));
				Timestamp timestamp = rs1.getTimestamp("POSITIONING_TIME");
				if (timestamp!=null) {
					position1.setResultpositionTime(sdf.format((Date)timestamp));
				}
//				infos.add(position1);
			}
			StringBuffer sql2=new StringBuffer("select top 1  vin,CUSTOM_NAME_CN,CREATE_USER,REPAIR_USER from pqiadatacar a where  vin in(select FRAME_NUM from BINDING where EQUIPMENT_NUM ='"+frameNumbr+"' and BING_TYPE='绑定') order by CREATE_TIME");
			st2=conn.createStatement();
			ResultSet rs2 = st2.executeQuery(sql2.toString());
			System.out.println(sql2);
			if(rs2.next()){
				position1.setChinesedescription(rs2.getString("CUSTOM_NAME_CN"));
				position1.setPersonliable(rs2.getString("CREATE_USER"));
				position1.setRepairman(rs2.getString("REPAIR_USER"));
				position1.setVin(rs2.getString("vin"));
				infos.add(position1);
			}
			}
			///////////////////////////////////////////
			
			
//			Iterator<PositionInfo> it = list.iterator();
//			while(it.hasNext()){
//				String frameNumbr = it.next().getFrameunmber();
////			    	//and equipment_num ='255.255.201.252'  order by POSITIONING_TIME desc
////			    	sql1.append("and equipment_num ='"+it.next()+"' order by POSITIONING_TIME desc");
////				}
//				//根据设备号取经纬度的语句
//				StringBuffer sql1=new StringBuffer("select top 1 * from positioning where 1=1 ");
////				if (it.next()!=null) {
//					sql1.append("and equipment_num ='"+frameNumbr+"'");
////				}else{
//					sql1.append(" order by POSITIONING_TIME desc");
//					
////				}
////			    sql1.append("and equipment_num ='"+it.next().getFrameunmber()+"'");
////			    if (it.next()==null) {
////				}else{
//				    
////				}
//			    conn = DBUtil.getConnection();
//				Statement st1 = conn.createStatement();
//				ResultSet rs1 = st1.executeQuery(sql1.toString());
//				System.out.println(sql1.toString());
//				if (rs1.next()) {
//					PositionInfo position1 = new PositionInfo();
//					position1.setLog(rs1.getDouble("LONGITUDE"));
//					position1.setLat(rs1.getDouble("LATITUDE"));
//					position1.setArea(rs1.getString("AREA"));
//					position1.setFrameNum(rs1.getString("equipment_num"));
//					infos.add(position1);
//			}
//				StringBuffer sql2=new StringBuffer("select top 1  CUSTOM_NAME_CN,CREATE_USER,REPAIR_USER from pqiadatacar a where  vin in(select FRAME_NUM from BINDING where EQUIPMENT_NUM ='"+frameNumbr+"' and BING_TYPE='绑定') order by CREATE_TIME");
//				Statement st2=conn.createStatement();
//				ResultSet rs2=st2.executeQuery(sql2.toString());
//				System.out.println(sql2);
//				if(rs2.next()){
//					PositionInfo position2=new PositionInfo();
//					position2.setChinesedescription(rs2.getString("CUSTOM_NAME_CN"));
//					position2.setPersonliable(rs2.getString("CREATE_USER"));
//					position2.setRepairman(rs2.getString("REPAIR_USER"));
//					infos.add(position2);
//				}
//			}
			System.out.println("汽车条件查询x,y执行完毕");
			
			result.setData(infos);
			result.setMessage("查询成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("查询失败");
			result.setStates(false);
			e.printStackTrace();
			} finally {
				if (st1 != null) {
					try {
						st1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (st2!=null) {
						try {
							st2.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return result;
		}
	
	//根据时间查询位置信息
		@Override
		public Result getInfoAsTime(PositionInfo info) {
			// TODO Auto-generated method stub
			Result result = new Result();
			if(info.getEquipmentNum()==null||info.getEquipmentNum().isEmpty()
					||info.getStartTime()==null||info.getStartTime().isEmpty()
					||info.getEndTime()==null||info.getEndTime().isEmpty()){
				result.setStates(false);
				result.setMessage("参数不能为空");
				return result;
			}
			
			List<PositionInfo> infos = new ArrayList<PositionInfo>();
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				String sql = "select * from positioning where equipment_num=? and positioning_time>? and positioning_time <?";
				conn = DBUtil.getConnection();
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, info.getEquipmentNum());
				ps.setString(2, info.getStartTime());
				ps.setString(3, info.getEndTime());
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					PositionInfo pInfo = new PositionInfo();
					pInfo.setEquipmentNum(rs.getString("equipment_num"));
					pInfo.setElec(rs.getInt("electricity"));
					pInfo.setLog(rs.getDouble("longitude"));
					pInfo.setLat(rs.getDouble("latitude"));
					pInfo.setPositionMode(rs.getString("positioning_mode"));
					pInfo.setPositionTime(rs.getTimestamp("positioning_time"));
					pInfo.setArea(rs.getString("area"));
					
					infos.add(pInfo);
				}
				
				result.setStates(true);
				result.setMessage("查询成功");
				result.setData(infos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result.setStates(false);
				result.setMessage("查询失败");
				e.printStackTrace();
			} finally{
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(ps!=null){
					try {
						ps.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return result;
		}


	@Override
	public PositionInfo getTimeAsMac(String mac) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement ps = null;
		PositionInfo info = new PositionInfo();
		try {
			String sql = "select top 1 * from positioning where equipment_num=? order by positioning_time desc";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, mac);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				info.setPositionTime(rs.getTimestamp("positioning_time"));
			}else{
				return null;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			info = null;
			e.printStackTrace();
		}finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return info;
	}

}
