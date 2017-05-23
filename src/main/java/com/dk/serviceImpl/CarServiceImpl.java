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

import com.dk.object.CarInfo;
import com.dk.result.Result;
import com.dk.service.CarService;
import com.dk.util.DBUtil;

@Service
public class CarServiceImpl implements CarService{

	public Result getCarInfos() {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<CarInfo> infos = new ArrayList<CarInfo>();
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DBUtil.getConnection();
			String sql  ="select * from carinfo limit 0,10";
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				CarInfo info = new CarInfo();
				info.setFrameNum(rs.getString("frame_num"));
				info.setEngineNum(rs.getString("engine_num"));
				info.setEquipmentNum(rs.getString("equipment_num"));
				Timestamp productionTime = rs.getTimestamp("production_time");
				info.setProductionTime(productionTime);
				if(productionTime!=null){
					info.setProductionTimeStr(sdf.format((Date)productionTime));
				}else{
					info.setProductionTimeStr("");
				}
				
				infos.add(info);
			}
			
			sql = "select count(*) as carCount from carinfo";
			rs = st.executeQuery(sql);
			if(rs.next()){
				result.setCount(rs.getInt("carCount"));
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

	public Result updateCarInfo(CarInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "update carinfo set engine_num=?,production_time=?,equipment_num=? where frame_num=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getEngineNum());
			ps.setTimestamp(2, info.getProductionTime());
			ps.setString(3, info.getEquipmentNum());
			ps.setString(4, info.getFrameNum());
			
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
	public Result getCarInfo(CarInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<CarInfo> infos = new ArrayList<CarInfo>();
		Connection conn = null;
		Statement st = null;
		StringBuffer sql = new StringBuffer("select * from carinfo where 1=1");
		if(info.getFrameNum()!=null&&!info.getFrameNum().isEmpty()){
			sql.append(" and frame_num='"+info.getFrameNum()+"'");
		}
		if(info.getEngineNum()!=null&&!info.getEngineNum().isEmpty()){
			sql.append(" and engine_num='"+info.getEngineNum()+"'");
		}
		if(info.getPage()==0){
			sql.append(" limit 0,10");
		}else{
			sql.append(" limit "+(info.getPage()-1)*10+","+info.getPage()*10);
		}
		
		
		try {
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			
			
			ResultSet rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				CarInfo carInfo = new CarInfo();
				carInfo.setFrameNum(rs.getString("frame_num"));
				carInfo.setEngineNum(rs.getString("engine_num"));
				carInfo.setEquipmentNum(rs.getString("equipment_num"));
				Timestamp productionTime = rs.getTimestamp("production_time");
				carInfo.setProductionTime(productionTime);
				if(productionTime!=null){
					carInfo.setProductionTimeStr(sdf.format((Date)productionTime));
				}
				infos.add(carInfo);
			}
			
			sql = new StringBuffer("select count(*) as carCount from carinfo where 1=1");
			if(info.getFrameNum()!=null&&!info.getFrameNum().isEmpty()){
				sql.append(" and frame_num='"+info.getFrameNum()+"'");
			}
			if(info.getEngineNum()!=null&&!info.getEngineNum().isEmpty()){
				sql.append(" and engine_num='"+info.getEngineNum()+"'");
			}
			rs = st.executeQuery(sql.toString());
			if(rs.next()){
				result.setCount(rs.getInt("carCount"));
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

	@SuppressWarnings("unchecked")
	@Override
	public Result addCarInfo(CarInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		result = getCarInfo(info);
		if(((ArrayList<CarInfo>)result.getData()).size()!=0){
			result.setStates(false);
			return result;
		}
		result = new Result();
		String sql = "insert into carinfo(frame_num,engine_num,production_time,equipment_num) values(?,?,?,?)";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getFrameNum());
			ps.setString(2, info.getEngineNum());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setString(4, info.getEquipmentNum());
			
			ps.execute();
			result.setStates(true);
			result.setMessage("保存成功");
			result.setData(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("保存是爱");
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
	public Result deleteCarInfo(CarInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from carinfo where frame_num=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getFrameNum());
			
			ps.execute();
			result.setData(info);
			result.setMessage("删除成功");
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
