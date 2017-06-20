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
import java.util.List;

import org.springframework.stereotype.Service;

import com.dk.object.PieData;
import com.dk.object.PositionInfo;
import com.dk.result.Result;
import com.dk.service.PositonService;
import com.dk.util.DBUtil;

@Service
public class PositionServiceImpl implements PositonService{

	public Result getInfos() {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<PositionInfo> infos = new ArrayList<PositionInfo>();
		Connection conn = null;
		Statement st = null;
		
		try {
//			String sql = "select * from positioning order by positioning_time desc limit 10";
			String sql = "select top 10 * from positioning order by positioning_time desc";
			String countSql = "select count(*) as sumcount from positioning";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
//			ResultSet rs = st.executeQuery(sql);
			ResultSet rs = st.executeQuery(countSql);
			if(rs.next()){
				result.setCount(rs.getInt("sumcount"));
			}
			rs = st.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				PositionInfo info = new PositionInfo();
				info.setEquipmentNum(rs.getString("equipment_num"));
				info.setElec(rs.getInt("electricity"));
				info.setLog(rs.getDouble("longitude"));
				info.setLat(rs.getDouble("latitude"));
				info.setPositionMode(rs.getString("positioning_mode"));
				info.setArea(rs.getString("area"));
//				info.setPositionTime(rs.getTimestamp("positioning_time"));
				
				Timestamp positionTime = rs.getTimestamp("positioning_time");
				info.setPositionTime(positionTime);
				if(positionTime!=null){
					info.setPositionTimeStr(sdf.format((Date)positionTime));
				}else{
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
		} finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(st!=null){
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

	
	//根据车架号查询设备位置信息
	@Override
	public Result getInfoAsFrameNum(String frameNum) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		PositionInfo info = new PositionInfo();
		
		try {
//			String sql = "select * from positioning where frame_num=?";
			String sql = "select top 1 * from positioning where equipment_num = (select equipment_num from binding where frame_num=? and bing_type=?) order by positioning_time desc";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, frameNum);
			ps.setString(2, "绑定");
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				info.setEquipmentNum(rs.getString("equipment_num"));
				info.setElec(rs.getInt("electricity"));
				info.setLog(rs.getDouble("longitude"));
				info.setLat(rs.getDouble("latitude"));
				info.setPositionMode(rs.getString("positioning_mode"));
				info.setPositionTime(rs.getTimestamp("positioning_time"));
				info.setArea(rs.getString("area"));
				info.setFrameNum(frameNum);
			}else{
				info=null;
			}
			
			result.setData(info);
			result.setMessage("查询成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("查询失败");
			result.setStates(false);
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
	public Result getInfo(PositionInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<PositionInfo> infos = new ArrayList<PositionInfo>();
		
		try {
			conn = DBUtil.getConnection();
			st = conn.createStatement();
//			StringBuffer sql = new StringBuffer("select * from positioning where 1= 1");
			StringBuffer sql = new StringBuffer("select top 10 id,* from positioning where 1= 1");
			StringBuffer countsql = new StringBuffer("select count(*) as sumcount from positioning where 1= 1");
			StringBuffer ssql = new StringBuffer();
			
			if(info.getEquipmentNum()!=null&&!info.getEquipmentNum().isEmpty()){
				sql.append(" and equipment_num like '%"+info.getEquipmentNum()+"'"); 
				countsql.append(" and equipment_num like '%"+info.getEquipmentNum()+"'");
				ssql.append(" and equipment_num like '%"+info.getEquipmentNum()+"'");
			}
			if(info.getElec()!=null){
				sql.append(" and electricity="+info.getElec());
				countsql.append(" and electricity="+info.getElec());
				ssql.append(" and electricity="+info.getElec());
			}
			if(info.getPositionMode()!=null&&!info.getPositionMode().isEmpty()){
				sql.append(" and positioning_mode='"+info.getPositionMode()+"'");
				countsql.append(" and positioning_mode='"+info.getPositionMode()+"'");
				ssql.append(" and positioning_mode='"+info.getPositionMode()+"'");
			}
			if(info.getArea()!=null&&!info.getArea().isEmpty()){
				sql.append(" and area = '"+info.getArea()+"'");
				countsql.append(" and area = '"+info.getArea()+"'");
				ssql.append(" and area = '"+info.getArea()+"'");
			}
//			if(info.getPage()!=0&&info.getTotal()!=0){
//				sql.append(" limit "+(info.getPage()-1)*info.getTotal()+","+info.getTotal());
//			}
			
//			sql.append(" limit "+info.getPage()*10+", 10");
			sql.append(" and id not in(select top "+info.getPage()*10+" id from positioning where 1 = 1 "+ ssql.toString() +" order by positioning_time desc)");
			sql.append(" order by positioning_time desc");
			
			ResultSet rs = st.executeQuery(countsql.toString());
			if(rs.next()){
				result.setCount(rs.getInt("sumcount"));
			}
			
			rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				PositionInfo position = new PositionInfo();
				position.setEquipmentNum(rs.getString("equipment_num"));
				position.setElec(rs.getInt("electricity"));
				position.setLog(rs.getDouble("longitude"));
				position.setLat(rs.getDouble("latitude"));
				position.setPositionMode(rs.getString("positioning_mode"));
				position.setArea(rs.getString("area"));
				Timestamp positionTime = rs.getTimestamp("positioning_time");
				position.setPositionTime(positionTime);
				if(positionTime!=null){
					position.setPositionTimeStr(sdf.format((Date)positionTime));
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
		} finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(st!=null){
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
			while(rs.next()){
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
		} finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(st!=null){
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
		if(info.getEquipmentNum()==null||info.getEquipmentNum().isEmpty()){
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
	public Result updateInfo(PositionInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getEquipmentNum()==null||info.getEquipmentNum().isEmpty()){
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
	public Result deleteInfo(PositionInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getEquipmentNum()==null||info.getEquipmentNum().isEmpty()){
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


	//低电量饼图
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
			if(rs.next()){
				count = rs.getInt("sumcount");
			}
			sql = "select count(distinct(equipment_num)) as lowcount from positioning where electricity <= 20";
			rs = st.executeQuery(sql);
			if(rs.next()){
				PieData info = new PieData();
				lowcount = rs.getInt("lowcount");
				info.setName("需要充电的设备");
				info.setValue(lowcount);
				infos.add(info);
			}
			if(count-lowcount>0){
				PieData info = new PieData();
				info.setName("正常设备");
				info.setValue(count-lowcount);
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
		} finally{
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(st!=null){
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

}
