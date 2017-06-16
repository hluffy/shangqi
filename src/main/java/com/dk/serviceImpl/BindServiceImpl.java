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

import com.dk.object.BindInfo;
import com.dk.object.PieData;
import com.dk.result.Result;
import com.dk.service.BindService;
import com.dk.util.DBUtil;

@Service
public class BindServiceImpl implements BindService{

	@Override
	public Result getBindInfos() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<BindInfo> infos = new ArrayList<BindInfo>();
		try {
//			String sql = "select * from binding order by bind_time desc limit 10 ";
			String sql = "select top 10 * from binding order by bind_time desc";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				BindInfo info = new BindInfo();
				info.setId(rs.getInt("id"));
				info.setFrameNum(rs.getString("frame_num"));
				info.setEquipmentNum(rs.getString("equipment_num"));
//				info.setBindTime(rs.getTimestamp("bind_time"));
//				info.setUnbindTime(rs.getTimestamp("unbind_time"));
				info.setBindType(rs.getString("bing_type"));
				Timestamp bindTime = rs.getTimestamp("bind_time");
				if(bindTime!=null){
					info.setBindTimeStr(sdf.format((Date)bindTime));
				}
				Timestamp unbindTime = rs.getTimestamp("unbind_time");
				if(unbindTime!=null){
					info.setUnbindTimeStr(sdf.format((Date)unbindTime));
				}
				
				infos.add(info);
			}
			
			sql = "select count(*) as bindCount from binding";
			rs = st.executeQuery(sql);
			if(rs.next()){
				result.setCount(rs.getInt("bindCount"));
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

	@Override
	public Result addBindInfo(BindInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getEquipmentNum()==null||info.getEquipmentNum().isEmpty()
				||info.getFrameNum()==null||info.getFrameNum().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into binding(frame_num,equipment_num,bind_time,unbind_time,bing_type) values(?,?,?,?,?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getFrameNum());
			ps.setString(2, info.getEquipmentNum());
			ps.setTimestamp(3, info.getBindTime());
			ps.setTimestamp(4, info.getUnbindTime());
			ps.setString(5, info.getBindType());
			
			ps.execute();
			
			result.setData(info);
			result.setMessage("绑定成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("绑定失败");
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
	public Result updateBindInfo(BindInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getEquipmentNum()==null||info.getEquipmentNum().isEmpty()
				||info.getFrameNum()==null||info.getFrameNum().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "update binding set bing_type=?,unbind_time=? where equipment_num=? and frame_num=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getBindType());
			ps.setTimestamp(2, info.getUnbindTime());
			ps.setString(3, info.getEquipmentNum());
			ps.setString(4, info.getFrameNum());
			
			ps.execute();
			
			result.setStates(true);
			result.setMessage("解绑成功");
			result.setData(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("解绑失败");
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
	public Result getBindInfo(BindInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<BindInfo> infos = new ArrayList<BindInfo>();
		StringBuffer sql = new StringBuffer("select top 10 id, * from binding where 1 =1 ");
		StringBuffer countSql = new StringBuffer("select count(*) as countBind from binding where 1 =1 ");
		StringBuffer ssql = new StringBuffer();
		if(info.getEquipmentNum()!=null&&!info.getEquipmentNum().isEmpty()){
			sql.append(" and equipment_num like '%"+info.getEquipmentNum()+"'");
			countSql.append(" and equipment_num like '%"+info.getEquipmentNum()+"'");
			ssql.append(" and equipment_num like '%"+info.getEquipmentNum()+"'");
		}
		if(info.getFrameNum()!=null&&!info.getFrameNum().isEmpty()){
			sql.append(" and frame_num like '%"+info.getFrameNum()+"'");
			countSql.append(" and frame_num like '%"+info.getFrameNum()+"'");
			ssql.append(" and frame_num like '%"+info.getFrameNum()+"'");
		}
		if(info.getBindType()!=null&&!info.getBindType().isEmpty()){
			sql.append(" and bing_type = '"+info.getBindType()+"'");
			countSql.append(" and bing_type = '"+info.getBindType()+"'");
			ssql.append(" and bing_type = '"+info.getBindType()+"'");
		}
		if(info.getBindStartTime()!=null&&!info.getBindStartTime().isEmpty()&&info.getBindEndTime()!=null&&!info.getBindEndTime().isEmpty()){
			sql.append(" and bind_time > '"+Timestamp.valueOf(info.getBindStartTime())+"'");
			sql.append(" and bind_time < '"+Timestamp.valueOf(info.getBindEndTime())+"'");
			countSql.append(" and bind_time > '"+Timestamp.valueOf(info.getBindStartTime())+"'");
			countSql.append(" and bind_time < '"+Timestamp.valueOf(info.getBindEndTime())+"'");
			ssql.append(" and bind_time > '"+Timestamp.valueOf(info.getBindStartTime())+"'");
			ssql.append(" and bind_time < '"+Timestamp.valueOf(info.getBindEndTime())+"'");
		}
		if(info.getUnbindStartTime()!=null&&!info.getUnbindStartTime().isEmpty()&&info.getUnbindEndTime()!=null&&!info.getUnbindEndTime().isEmpty()){
			sql.append(" and unbind_time > '"+Timestamp.valueOf(info.getUnbindStartTime())+"'");
			sql.append(" and unbind_time < '"+Timestamp.valueOf(info.getUnbindEndTime())+"'");
			countSql.append(" and unbind_time > '"+Timestamp.valueOf(info.getUnbindStartTime())+"'");
			countSql.append(" and unbind_time < '"+Timestamp.valueOf(info.getUnbindEndTime())+"'");
			ssql.append(" and unbind_time > '"+Timestamp.valueOf(info.getUnbindStartTime())+"'");
			ssql.append(" and unbind_time < '"+Timestamp.valueOf(info.getUnbindEndTime())+"'");
		}
		
//		if(info.getPage()==0){
//			sql.append(" limit 0,10");
//		}else{
//			sql.append(" limit "+info.getPage()*10+",10");
//		}
		sql.append(" and id not in (select top "+info.getPage()*10+" id from binding where 1 = 1 "+ssql.toString()+" order by bind_time desc)");
		sql.append(" order by bind_time desc");
		
		try {
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(countSql.toString());
			if(rs.next()){
				result.setCount(rs.getInt("countBind"));
			}
			rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				BindInfo bInfo = new BindInfo();
				bInfo.setId(rs.getInt("id"));
				bInfo.setEquipmentNum(rs.getString("equipment_num"));
				bInfo.setFrameNum(rs.getString("frame_num"));
				bInfo.setBindType(rs.getString("bing_type"));
				Timestamp bindTime = rs.getTimestamp("bind_time");
				if(bindTime!=null){
					bInfo.setBindTime(bindTime);
					bInfo.setBindTimeStr(sdf.format((Date)bindTime));
				}
				Timestamp unbindTime = rs.getTimestamp("unbind_time");
				if(unbindTime!=null){
					bInfo.setUnbindTime(unbindTime);
					bInfo.setUnbindTimeStr(sdf.format((Date)unbindTime));
				}
				
				
				infos.add(bInfo);
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
	public Result getBindInfoForCharts() {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<PieData> infos = new ArrayList<PieData>();
		try {
			String sql = "select count(*) as sumcount,bing_type from binding group by bing_type";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				PieData info = new PieData();
				info.setName(rs.getString("bing_type"));
				info.setValue(rs.getInt("sumcount"));
				infos.add(info);
			}
			result.setMessage("查询成功");
			result.setStates(true);
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

}
