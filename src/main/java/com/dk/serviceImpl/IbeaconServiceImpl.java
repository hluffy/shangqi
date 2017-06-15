package com.dk.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dk.object.IbeaconInfo;
import com.dk.object.PieData;
import com.dk.result.Result;
import com.dk.service.IbeaconService;
import com.dk.util.DBUtil;

@Service
public class IbeaconServiceImpl implements IbeaconService{

	public Result getInfos() {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<IbeaconInfo> infos = new ArrayList<IbeaconInfo>();
		Connection conn = null;
		Statement st = null;
		
		try {
			String countSql = "select count(*) as sumcount from ibeacon";
//			String sql = "select * from ibeacon order by last_use_time desc limit 10";
			String sql = "select top 10 * from ibeacon order by last_use_time desc";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(countSql);
			if(rs.next()){
				result.setCount(rs.getInt("sumcount"));
			}
			rs = st.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				IbeaconInfo info = new IbeaconInfo();
				info.setMinor(rs.getInt("minor"));
				info.setUuid(rs.getString("uuid"));
				info.setLog(rs.getDouble("longitude"));
				info.setLat(rs.getDouble("latitude"));
				info.setEle(rs.getInt("ele"));
				info.setArea(rs.getString("area"));
				Timestamp lastUseTime = rs.getTimestamp("last_use_time");
				info.setLastUseTime(lastUseTime);
				if(lastUseTime!=null){
					info.setLastUseTimeStr(sdf.format((Date)lastUseTime));
				}
				
				infos.add(info);
			}
			result.setData(infos);
			result.setStates(true);
			result.setMessage("操作成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("操作失败");
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

	public Result updateInfo(IbeaconInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getUuid()==null){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "update ibeacon set longitude=?,latitude=?,ele=?,area=?,last_use_time=? where uuid=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, info.getLog());
			ps.setDouble(2, info.getLat());
			if(info.getEle()!=null){
				ps.setInt(3, info.getEle());
			}else{
				ps.setInt(3, 0);
			}
			ps.setString(4, info.getArea());
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			ps.setString(6, info.getUuid());
			
			ps.execute();
			result.setData(info);
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
	public Result getInfo(IbeaconInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<IbeaconInfo> infos = new ArrayList<IbeaconInfo>();
		Connection conn = null;
		Statement st = null;
		StringBuffer sql = new StringBuffer("select top 10 uuid, * from ibeacon where 1=1");
		StringBuffer countSql = new StringBuffer("select count(*) as sumcount from ibeacon where 1 =1 ");
		StringBuffer ssql = new StringBuffer();
		if(info.getMinor()!=null){
			sql.append(" and minor="+info.getMinor());
			countSql.append(" and minor="+info.getMinor());
			ssql.append(" and minor="+info.getMinor());
		}
		if(info.getUuid()!=null&&!info.getUuid().isEmpty()){
			sql.append(" and uuid like '%"+info.getUuid()+"'");
			countSql.append(" and uuid like '%"+info.getUuid()+"'");
			ssql.append(" and uuid like '%"+info.getUuid()+"'");
		}
		if(info.getEle()!=null){
			sql.append(" and ele='"+info.getEle()+"'");
			countSql.append(" and ele='"+info.getEle()+"'");
			ssql.append(" and ele='"+info.getEle()+"'");
		}
		if(info.getArea()!=null&&!info.getArea().isEmpty()){
			sql.append(" and area='"+info.getArea()+"'");
			countSql.append(" and area='"+info.getArea()+"'");
			ssql.append(" and area='"+info.getArea()+"'");
		}
		if(info.getStartTimeStr()!=null&&!info.getStartTimeStr().isEmpty()&&info.getEndTimeStr()!=null&&!info.getEndTimeStr().isEmpty()){
			sql.append(" and last_use_time<'"+Timestamp.valueOf(info.getEndTimeStr())+"'");
			sql.append(" and last_use_time>'"+Timestamp.valueOf(info.getStartTimeStr())+"'");
			countSql.append(" and last_use_time<'"+Timestamp.valueOf(info.getEndTimeStr())+"'");
			countSql.append(" and last_use_time>'"+Timestamp.valueOf(info.getStartTimeStr())+"'");
			ssql.append(" and last_use_time<'"+Timestamp.valueOf(info.getEndTimeStr())+"'");
			ssql.append(" and last_use_time>'"+Timestamp.valueOf(info.getStartTimeStr())+"'");
		}
		
//		if(info.getPage()==0){
//			sql.append(" limit 0,10");
//		}else{
//			sql.append(" limit "+info.getPage()*10+","+10);
//		}
		sql.append(" and uuid not in ( select top "+info.getPage()*10+" uuid from ibeacon where 1 = 1 "+ ssql.toString() +" order by last_use_time desc)");
		sql.append(" order by last_use_time desc");
		try {
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(countSql.toString());
			if(rs.next()){
				result.setCount(rs.getInt("sumcount"));
			}
			rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				IbeaconInfo ibeaconInfo = new IbeaconInfo();
				ibeaconInfo.setMinor(rs.getInt("minor"));
				ibeaconInfo.setUuid(rs.getString("uuid"));
				ibeaconInfo.setLat(rs.getDouble("latitude"));
				ibeaconInfo.setLog(rs.getDouble("longitude"));
				ibeaconInfo.setEle(rs.getInt("ele"));
				ibeaconInfo.setArea(rs.getString("area"));
				Timestamp lastUseTime = rs.getTimestamp("last_use_time");
				ibeaconInfo.setLastUseTime(lastUseTime);
				if(lastUseTime!=null){
					ibeaconInfo.setLastUseTimeStr(sdf.format((Date)lastUseTime));
				}
				
				infos.add(ibeaconInfo);
			}
			result.setData(infos);
			result.setMessage("操作成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("操作失败");
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

	@SuppressWarnings("unchecked")
	@Override
	public Result addInfo(IbeaconInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getUuid()==null||info.getUuid().isEmpty()
				||info.getLog()==null
				||info.getLat()==null){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		if(info.getPage()==null){
			info.setPage(0);
		}
		result = getInfo(info);
		if(((ArrayList<IbeaconInfo>)result.getData()).size()>0){
			result.setStates(false);
			result.setMessage("该数据已存在");
			return result;
		}
		result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String sql = "insert into ibeacon(uuid,longitude,latitude,ele,area,last_use_time) values(?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
//			ps.setInt(1, info.getMinor());
			ps.setString(1, info.getUuid());
			ps.setDouble(2, info.getLog());
			ps.setDouble(3, info.getLat());
			if(info.getEle()!=null){
				ps.setInt(4, info.getEle());
			}else{
				ps.setInt(4, 0);
			}
			ps.setString(5, info.getArea());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			
			ps.execute();
			result.setStates(true);
			result.setData(info);
			result.setMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("操作失败");
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
	public Result deleteInfo(IbeaconInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getMinor()==null){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String sql = "delete from ibeacon where uuid=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getUuid());
			
			ps.execute();
			result.setStates(true);
			result.setData(info);
			result.setMessage("删除成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("删除失败");
			result.setStates(true);
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
	public Result getIbeaconForMap() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<IbeaconInfo> infos = new ArrayList<IbeaconInfo>();
		try {
			String sql = "select * from ibeacon";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				IbeaconInfo info = new IbeaconInfo();
				info.setMinor(rs.getInt("minor"));
				info.setUuid(rs.getString("uuid"));
				info.setLog(rs.getDouble("longitude"));
				info.setLat(rs.getDouble("latitude"));
				info.setEle(rs.getInt("ele"));
				info.setArea(rs.getString("area"));
				Timestamp lastUseTime = rs.getTimestamp("last_use_time");
				info.setLastUseTime(lastUseTime);
				
				infos.add(info);
			}
			
			result.setData(infos);
			result.setStates(true);
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

	@Override
	public Result getInfoAsUuid(IbeaconInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		IbeaconInfo ibeacon = new IbeaconInfo();
		
		try {
			String sql = "select * from ibeacon where uuid like ?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,"%"+info.getUuid());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ibeacon.setUuid(rs.getString("uuid"));
				ibeacon.setMinor(rs.getInt("minor"));
				ibeacon.setLat(rs.getDouble("latitude"));
				ibeacon.setLog(rs.getDouble("longitude"));
				ibeacon.setEle(rs.getInt("ele"));
				ibeacon.setArea(rs.getString("area"));
			}
			
			result.setStates(true);
			result.setMessage("查询成功");
			result.setData(ibeacon);
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
	public Result getInfoAsArea() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<PieData> infos = new ArrayList<PieData>();
		try {
			String sql = "select area,count(*) as sumcount from ibeacon group by area";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				PieData info = new PieData();
				info.setName(rs.getString("area"));
				info.setValue(rs.getInt("sumcount"));
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

}
