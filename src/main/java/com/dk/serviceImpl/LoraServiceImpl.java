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

import com.dk.object.LoraInfo;
import com.dk.result.Result;
import com.dk.service.LoraService;
import com.dk.util.DBUtil;

@Service
public class LoraServiceImpl implements LoraService{

	@Override
	public Result getInfos() {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<LoraInfo> infos = new ArrayList<LoraInfo>();
		Connection conn = null;
		Statement st = null;
		try {
//			String sql = "select * from lora order by time desc limit 10";
			String sql = "select top 10 * from lora order by time desc";
			String countSql = "select count(*) as sumcount from lora";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(countSql);
			if(rs.next()){
				result.setCount(rs.getInt("sumcount"));
			}
			rs = st.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				LoraInfo info = new LoraInfo();
				info.setNumber(rs.getString("number"));
				info.setIp(rs.getString("ip"));
				info.setRegistAddr(rs.getString("registaddr"));
				info.setRegistPort(rs.getString("registport"));
				info.setLoginAddr(rs.getString("loginaddr"));
				info.setTerNum(rs.getString("ternum"));
				Timestamp time = rs.getTimestamp("time");
				info.setTime(time);
				if(time!=null){
					info.setTimeStr(sdf.format((Date)time));
				}
				
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
	public Result addInfo(LoraInfo info) {
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
			String sql = "insert into lora(number,ip,registaddr,registport,loginaddr,ternum,time) values(?,?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, info.getNumber());
			ps.setString(2, info.getIp());
			ps.setString(3, info.getRegistAddr());
			ps.setString(4, info.getRegistPort());
			ps.setString(5, info.getLoginAddr());
			ps.setString(6, info.getTerNum());
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			
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

	@Override
	public Result updateInfo(LoraInfo info) {
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
			String sql = "update lora set ip=?,registaddr=?,registport=?,loginaddr=?,ternum=?,time=? where number=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, info.getIp());
			ps.setString(2, info.getRegistAddr());
			ps.setString(3, info.getRegistPort());
			ps.setString(4, info.getLoginAddr());
			ps.setString(5, info.getTerNum());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.setString(7, info.getNumber());
			
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
	public Result deleteInfo(LoraInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getNumber()==null||info.getNumber().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "delete from lora where number = ?";
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
	public Result getInfo(LoraInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<LoraInfo> infos = new ArrayList<LoraInfo>();
		try {
			StringBuffer sql = new StringBuffer("select top 10 number, * from lora where 1 = 1 ");
			StringBuffer countSql = new StringBuffer("select count(*) as sumcount from lora where 1 = 1 ");
			StringBuffer ssql = new StringBuffer();
			if(info.getNumber()!=null&&!info.getNumber().isEmpty()){
				sql.append(" and number ='"+info.getNumber()+"'");
				countSql.append(" and number='"+info.getNumber()+"'");
				ssql.append(" and number ='"+info.getNumber()+"'");
			}
			if(info.getIp()!=null&&!info.getIp().isEmpty()){
				sql.append(" and ip = '"+info.getIp()+"'");
				countSql.append(" and ip = '"+info.getIp()+"'");
				ssql.append(" and ip = '"+info.getIp()+"'");
			}
			if(info.getRegistAddr()!=null&&!info.getRegistAddr().isEmpty()){
				sql.append(" and registaddr = '"+info.getRegistAddr()+"'");
				countSql.append(" and registaddr = '"+info.getRegistAddr()+"'");
				ssql.append(" and registaddr = '"+info.getRegistAddr()+"'");
			}
			if(info.getRegistPort()!=null&&!info.getRegistPort().isEmpty()){
				sql.append(" and registport = '"+info.getRegistPort()+"'");
				countSql.append(" and registport = '"+info.getRegistPort()+"'");
				ssql.append(" and registport = '"+info.getRegistPort()+"'");
			}
			if(info.getLoginAddr()!=null&&info.getLoginAddr().isEmpty()){
				sql.append(" and loginaddr = '"+info.getLoginAddr()+"'");
				countSql.append(" and loginaddr = '"+info.getLoginAddr()+"'");
				ssql.append(" and loginaddr = '"+info.getLoginAddr()+"'");
			}
			if(info.getTerNum()!=null&&!info.getTerNum().isEmpty()){
				sql.append(" and ternum = '"+info.getTerNum()+"'");
				countSql.append(" and ternum = '"+info.getTerNum()+"'");
				ssql.append(" and ternum = '"+info.getTerNum()+"'");
			}
			
//			if(info.getPage()==0){
//				sql.append(" limit 0,10");
//			}else{
//				sql.append(" limit "+info.getPage()*10+","+10);
//			}
			sql.append(" and number not in (select top "+info.getPage()*10+" number from lora where 1 = 1 "+ ssql.toString() +" order by time desc)");
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
				LoraInfo lInfo = new LoraInfo();
				lInfo.setNumber(rs.getString("number"));
				lInfo.setIp(rs.getString("ip"));
				lInfo.setRegistAddr(rs.getString("registaddr"));
				lInfo.setRegistPort(rs.getString("registport"));
				lInfo.setLoginAddr(rs.getString("loginaddr"));
				lInfo.setTerNum(rs.getString("ternum"));
				Timestamp time = rs.getTimestamp("time");
				lInfo.setTime(time);
				if(time!=null){
					lInfo.setTimeStr(sdf.format((Date)time));
				}
				
				infos.add(lInfo);
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

}
