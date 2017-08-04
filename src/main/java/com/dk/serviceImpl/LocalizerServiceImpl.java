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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dk.object.LocalizerInfo;
import com.dk.object.PieData;
import com.dk.result.Result;
import com.dk.service.LocalizerService;
import com.dk.util.DBUtil;

@Service
public class LocalizerServiceImpl implements LocalizerService{

	@Override
	public Result getInfos() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<LocalizerInfo> infos = new ArrayList<LocalizerInfo>();
		try {
//			String sql = "select * from localizer order by time desc limit 0,10";
			String sql = "select top 10 * from localizer order by time desc";
			String countSql = "select count(*) as sumcount from localizer";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(countSql);
			if(rs.next()){
				result.setCount(rs.getInt("sumcount"));
			}
			rs = st.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				LocalizerInfo info = new LocalizerInfo();
				info.setNumber(rs.getString("number"));
				info.setStaticTime(rs.getString("static_time"));
				info.setRunTime(rs.getString("run_time"));
				info.setLog(rs.getString("log"));
				info.setLat(rs.getString("lat"));
				info.setEle(rs.getInt("ele"));
				Timestamp time = rs.getTimestamp("time");
				info.setTime(time);
				if(time!=null){
					info.setTimeStr(sdf.format((Date)time));
				}
				info.setNumberDef(rs.getString("number_def"));
				info.setSv(rs.getString("sv"));
				info.setSvStr(rs.getString("sv_str"));
				info.setGpsTimeOut(rs.getString("gps_time_out"));
				info.setLoraSleepTime(rs.getString("lora_sleep_time"));
				
				info.setIbeaconEffectNum(rs.getString("ibeacon_effect_num"));
				info.setIbeaconTimeOut(rs.getString("ibeacon_timeout"));
				
				infos.add(info);
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
	public Result addInfo(LocalizerInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getNumberDef()==null||info.getNumberDef().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into localizer(number,static_time,run_time,time,log,lat,area,ele,number_def,gps_time_out,lora_sleep_time,ibeacon_effect_num,ibeacon_timeout) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
//			ps.setString(1, info.getNumber());
			ps.setString(1, numberToNumberDef(info.getNumberDef()));
			ps.setString(2, info.getStaticTime());
			ps.setString(3, info.getRunTime());
			ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			ps.setString(5, info.getLog());//经度
			ps.setString(6, info.getLat());//纬度
			ps.setString(7, info.getArea());
			if(info.getEle()==null){
				ps.setInt(8, 0);
			}else{
				ps.setInt(8, info.getEle());
			}
//			ps.setString(9, info.getNumber().replace(".", ""));
			ps.setString(9, info.getNumberDef());
			ps.setString(10, info.getGpsTimeOut());
			ps.setString(11, info.getLoraSleepTime());
			
			ps.setString(12, info.getIbeaconEffectNum());
			ps.setString(13, info.getIbeaconTimeOut());
			
			ps.execute();
			
			result.setStates(true);
			result.setMessage("添加成功");
			result.setData(info);
			result.setId(info.getNumber());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("添加失败");
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
	
	private String numberToNumberDef(String str){
		StringBuffer number = new StringBuffer();
		for(int i=1;i<=str.length();i++){
			number.append(str.charAt(i-1));
			if(i%3==0){
				number.append(".");
			}
		}
		return number.toString().substring(0,number.toString().length()-1);
	}

	@Override
	public Result updateInfo(LocalizerInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getNumber()==null||info.getNumber().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		if(info.getRunTime()==null||info.getRunTime().isEmpty()){
			info.setRunTime(String.valueOf(3*60));
		}
		try {
			String sql = "update localizer set static_time=?,run_time=?,time=?,gps_time_out=?,lora_sleep_time=?,ibeacon_effect_num=?,ibeacon_timeout=? where number=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, info.getStaticTime());
			ps.setString(2, info.getRunTime());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			
			ps.setString(4, info.getGpsTimeOut());
			ps.setString(5, info.getLoraSleepTime());
			
			ps.setString(6, info.getIbeaconEffectNum());
			ps.setString(7, info.getIbeaconTimeOut());
			
			ps.setString(8, info.getNumber());
			
			ps.execute();
			
			result.setStates(true);
			result.setMessage("更新成功");
			result.setData(info);
			result.setId(info.getNumber());
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
	public Result deleteInfo(LocalizerInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getNumber()==null||info.getNumber().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from localizer where number=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, info.getNumber());
			
			ps.execute();
			
			result.setStates(true);
			result.setMessage("删除成功");
			result.setData(info);
			result.setId(info.getNumber());
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

	@Override
	public Result getInfo(LocalizerInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<LocalizerInfo> infos = new ArrayList<LocalizerInfo>();
		if(info.getPage()==null){
			info.setPage(0);
		}
		try {
			StringBuffer sql = new StringBuffer("select top 10 number_def,* from localizer where 1 =1 ");
			StringBuffer countSql = new StringBuffer("select count(*) as sumcount from localizer where 1 =1 ");
			StringBuffer ssql = new StringBuffer();
			if(info.getNumber()!=null&&!info.getNumber().isEmpty()){
				sql.append(" and number like '%"+info.getNumber()+"'");
				countSql.append(" and number like '%"+info.getNumber()+"'");
				ssql.append(" and number like '%"+info.getNumber()+"'");
			}
			if(info.getStaticTime()!=null&&!info.getStaticTime().isEmpty()){
				sql.append(" and static_time='"+info.getStaticTime()+"'");
				countSql.append(" and static_time = '"+info.getStaticTime()+"'");
				ssql.append(" and static_time='"+info.getStaticTime()+"'");
			}
			if(info.getRunTime()!=null&&!info.getRunTime().isEmpty()){
				sql.append(" and run_time='"+info.getRunTime()+"'");
				countSql.append(" and run_time='"+info.getRunTime()+"'");
				ssql.append(" and run_time='"+info.getRunTime()+"'");
			}
			if(info.getArea()!=null&&!info.getArea().isEmpty()){
				sql.append(" and area='"+info.getArea()+"'");
				countSql.append(" and area='"+info.getArea()+"'");
				ssql.append(" and area='"+info.getArea()+"'");
			}
			
//			if(info.getPage()==0){
//				sql.append(" limit 0,10");
//			}else{
//				sql.append(" limit "+info.getPage()*10+","+10);
//			}
			sql.append(" and number_def not in (select top "+info.getPage()*10+" number_def from localizer where 1 = 1 "+ ssql.toString() +" order by time desc)");
			sql.append(" order by time desc");
			
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(countSql.toString());
			if(rs.next()){
				result.setCount(rs.getInt("sumcount"));
			}
			rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				LocalizerInfo lInfo = new LocalizerInfo();
				lInfo.setNumber(rs.getString("number"));
				lInfo.setStaticTime(rs.getString("static_time"));
				lInfo.setRunTime(rs.getString("run_time"));
				lInfo.setLog(rs.getString("log"));
				lInfo.setLat(rs.getString("lat"));
				lInfo.setArea(rs.getString("area"));
				Timestamp time = rs.getTimestamp("time");
				lInfo.setTime(time);
				if(time!=null){
					lInfo.setTimeStr(sdf.format((Date)time));
				}
				lInfo.setNumberDef(rs.getString("number_def"));
				lInfo.setSv(rs.getString("sv"));
				lInfo.setSvStr(rs.getString("sv_str"));
				lInfo.setGpsTimeOut(rs.getString("gps_time_out"));
				lInfo.setLoraSleepTime(rs.getString("lora_sleep_time"));
				
				lInfo.setIbeaconEffectNum(rs.getString("ibeacon_effect_num"));
				lInfo.setIbeaconTimeOut(rs.getString("ibeacon_timeout"));
				lInfo.setEle(rs.getInt("ele"));
				
				infos.add(lInfo);
			}
			
			result.setStates(true);
			result.setMessage("查询成功");
			result.setData(infos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(true);
			result.setMessage("查询成功");
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
	public Result getAreaData() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		Map<String,Integer> map = getMap();
		List<LocalizerInfo> infos = new ArrayList<LocalizerInfo>();
		try {
			String sql = "select area,count(*) as sumcount from localizer group by area";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				LocalizerInfo info = new LocalizerInfo();
				info.setArea(rs.getString("area"));
				info.setSumCount(rs.getInt("sumcount"));
				
				infos.add(info);
			}
			
			sql = "select count(*) as sumsum from localizer";
			rs = st.executeQuery(sql);
			if(rs.next()){
				result.setCount(rs.getInt("sumsum"));
			}
			
			for (LocalizerInfo info : infos) {
				map.put(info.getArea(),info.getSumCount());
			}
			
			infos = new ArrayList<LocalizerInfo>();
			for(String key:map.keySet()){
				LocalizerInfo info = new LocalizerInfo();
				info.setArea(key);
				info.setSumCount(map.get(key));
				infos.add(info);
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
	public Result getInfoOfArea(LocalizerInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<LocalizerInfo> infos = new ArrayList<LocalizerInfo>();
		try {
			StringBuffer sql = new StringBuffer("select * from localizer where 1=1");
			if(info.getArea()!=null&&!info.getArea().isEmpty()){
				sql.append(" and area = '"+info.getArea()+"'");
			}
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			
			
			ResultSet rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				LocalizerInfo lInfo = new LocalizerInfo();
				lInfo.setNumber(rs.getString("number"));
				lInfo.setLog(rs.getString("log"));
				lInfo.setLat(rs.getString("lat"));
				Timestamp time = rs.getTimestamp("time");
				lInfo.setTime(time);
				lInfo.setTimeStr(sdf.format((Date)time));
				
				infos.add(lInfo);
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
	public Result getEquipInfoForPie() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		int count = 0;
		int bindcount = 0;
		List<PieData> infos = new ArrayList<PieData>();
		try {
			String sql = "select count(*) as sumcount from localizer";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				count = rs.getInt("sumcount");
			}
			sql = "select count(*) as bindcount from localizer where number in (select equipment_num from binding where bing_type='绑定')";
			rs = st.executeQuery(sql);
			if(rs.next()){
				PieData info = new PieData();
				info.setName("已绑定设备");
				bindcount = rs.getInt("bindcount");
				info.setValue(bindcount);
				infos.add(info);
			}
			if(count-bindcount>0){
				PieData info = new PieData();
				info.setName("剩余设备");
				info.setValue(count-bindcount);
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

	//更新电量和区域
	@Override
	public Result updateLocalInfo(LocalizerInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String sql = "update localizer set area=?,ele=?,time=?,sv=?,sv_str=?,log=?,lat=? where number=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getArea());
			ps.setInt(2, info.getEle());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setString(4, info.getSv());
			ps.setString(5, info.getSvStr());
			ps.setString(6, info.getLog());
			ps.setString(7, info.getLat());
			ps.setString(8, info.getNumber());
			
			ps.execute();
			result.setMessage("更新成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("更新失败");
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

	//设备电量饼图
	@Override
	public Result lowInfo() {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<PieData> infos = new ArrayList<PieData>();
		Connection conn = null;
		Statement st = null;
		
		try {
			String sql = "select count(*) as sumcount from localizer";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			int count = 0;
			int lowcount = 0;
			ResultSet rs = st.executeQuery(sql);
			if(rs.next()){
				count = rs.getInt("sumcount");
			}
			sql = "select count(*) as lowcount from localizer where ele <= 20";
			rs = st.executeQuery(sql);
			if(rs.next()){
				PieData info = new PieData();
				info.setName("要充电的设备");
				lowcount = rs.getInt("lowcount");
				info.setValue(lowcount);
				infos.add(info);
			}
			if(count-lowcount>0){
				PieData info = new PieData();
				info.setName("正常的设备");
				info.setValue(count-lowcount);
				infos.add(info);
			}
			result.setStates(true);
			result.setData(infos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	private Map<String,Integer> getMap(){
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("CARE", 0);
		map.put("DVT返修区", 0);
		map.put("板链返修区", 0);
		map.put("内饰门线", 0);
		map.put("底盘三", 0);
		map.put("底盘一二", 0);
		map.put("报交区", 0);
		map.put("总装滞留区", 0);
		map.put("物流区", 0);
		map.put("检测区", 0);
		map.put("油漆返修区", 0);
		map.put("车身返修区", 0);
		map.put("扣车区", 0);
		map.put("车身滞留区", 0);
		map.put("其他", 0);
		return map;
	}

	@Override
	public Result getInfoForArea() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<PieData> infos = new ArrayList<PieData>();
		try {
			String sql = "select area,count(*) as sumcount from localizer group by area";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				PieData info = new PieData();
				info.setName(rs.getString("area"));
				info.setValue(rs.getInt("sumcount"));
				
				infos.add(info);
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

}
