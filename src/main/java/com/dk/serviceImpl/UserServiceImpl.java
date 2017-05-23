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

import com.dk.object.User;
import com.dk.result.Result;
import com.dk.service.UserService;
import com.dk.util.DBUtil;
import com.dk.util.Md5Util;

@Service
public class UserServiceImpl implements UserService{

	public Result getUserInfos() {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<User> users = new ArrayList<User>();
		Connection conn = null;
		Statement st = null;
		
//		String sql = "select * from userinfo order by create_time desc limit 0,10";
		String sql = "select top 10 * from userinfo order by create_time desc";
		try {
//			conn = HsqlDB.getConnection();
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				User user = new User();
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNum(rs.getString("phone_number"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				Timestamp createTime = rs.getTimestamp("create_time");
				user.setCreateTime(createTime);
				if(createTime!=null){
					user.setCreateTimeStr(sdf.format((Date)createTime));
				}else{
					user.setCreateTimeStr("");
				}
				users.add(user);
			}
			
			sql = "select count(*) as userCount from userinfo";
			rs = st.executeQuery(sql);
			if(rs.next()){
				result.setCount(rs.getInt("userCount"));
			}
			
			result.setData(users);
			result.setMessage("操作成功");
			result.setStates(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	public Result updateUserInfo(User user) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(user.getUserName()==null||user.getUserName().isEmpty()
				||user.getPassword()==null||user.getPassword().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		
		String sql = "update userinfo set phone_number=?,email=?,create_time=?,role=? where user_name=?";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getPhoneNum());
			ps.setString(2, user.getEmail());
			ps.setTimestamp(3, user.getCreateTime());
			ps.setString(4, user.getRole());
			ps.setString(5, user.getUserName());
			
			ps.execute();
			result.setData(user);
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

	public Result addUserInfo(User info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getUserName().isEmpty()||info.getPassword().isEmpty()){
			result.setMessage("用户名或密码不可以为空");
			result.setStates(false);
			return result;
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			User user = getUserAsUserName(info.getUserName());
			if(user!=null){
				result.setMessage("用户名已存在");
				result.setStates(false);
				return result;
			}
			
			
			
			String sql = "insert into userinfo(user_name,password,phone_number,email,create_time,role) values(?,?,?,?,?,?)";
			
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getUserName());
			ps.setString(2, Md5Util.md5String(info.getPassword()));
			ps.setString(3, info.getPhoneNum());
			ps.setString(4, info.getEmail());
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			ps.setString(6, info.getRole());
			
			ps.execute();
			result.setData(info);
			result.setMessage("保存成功");
			result.setStates(true);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setMessage("保存失败");
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
	
	public User getUserAsUserName(String userName){
		User info = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from userinfo where user_name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				info = new User();
				info.setUserName(userName);
				info.setPassword(rs.getString("password"));
				info.setPhoneNum(rs.getString("phone_number"));
				info.setEmail(rs.getString("email"));
				info.setCreateTime(rs.getTimestamp("create_time"));
				info.setRole(rs.getString("role"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		return info;
	}

	@Override
	public Result deleteUserInfo(User info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			String sql = "delete from userinfo where user_name =?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getUserName());
			ps.execute();
			
			result.setData(info);
			result.setStates(true);
			result.setMessage("删除成功");
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
	public Result loginUserInfo(User info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		User userInfo = new User();
		
		try {
			String sql = "select * from userinfo where user_name =? and password = ?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getUserName());
			ps.setString(2, Md5Util.md5String(info.getPassword()));
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				userInfo.setUserName(info.getUserName());
				userInfo.setPassword(info.getPassword());
				userInfo.setPhoneNum(rs.getString("phone_number"));
				userInfo.setEmail(rs.getString("email"));
				userInfo.setCreateTime(rs.getTimestamp("create_time"));
				userInfo.setRole(rs.getString("role"));
				
				result.setData(userInfo);
			}else{
				result.setData(null);
			}
			
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
	public Result getUserInfo(User info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<User> infos = new ArrayList<User>();
//		StringBuffer sql = new StringBuffer("select * from userinfo where 1 =1 ");
		StringBuffer sql = new StringBuffer("select top 10 user_name,* from userinfo where 1=1 ");
		StringBuffer countSql = new StringBuffer("select count(*) as userCount from userinfo where 1=1 ");
		StringBuffer ssql = new StringBuffer();
		
		try {
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			if(info.getUserName()!=null&&!info.getUserName().isEmpty()){
				sql.append(" and user_name='"+info.getUserName()+"'");
				countSql.append(" and user_name='"+info.getUserName()+"'");
				ssql.append(" and user_name='"+info.getUserName()+"'");
			}
			if(info.getPassword()!=null&&!info.getPassword().isEmpty()){
				sql.append(" and password ='"+Md5Util.md5String(info.getPassword())+"'");
				countSql.append(" and password ='"+Md5Util.md5String(info.getPassword())+"'");
				ssql.append(" and password ='"+Md5Util.md5String(info.getPassword())+"'");
			}
			if(info.getPhoneNum()!=null&&!info.getPhoneNum().isEmpty()){
				sql.append(" and phone_number='"+info.getPhoneNum()+"'");
				countSql.append(" and phone_number='"+info.getPhoneNum()+"'");
				ssql.append(" and phone_number='"+info.getPhoneNum()+"'");
			}
			if(info.getEmail()!=null&&!info.getEmail().isEmpty()){
				sql.append(" and email='"+info.getEmail()+"'");
				countSql.append(" and email='"+info.getEmail()+"'");
				ssql.append(" and email='"+info.getEmail()+"'");
			}
			if(info.getRole()!=null&&!info.getRole().isEmpty()){
				sql.append(" and role ='"+info.getRole()+"'");
				countSql.append(" and role ='"+info.getRole()+"'");
				ssql.append(" and role ='"+info.getRole()+"'");
			}
			
			
//			if(info.getPage()==0){
//				sql.append(" limit 0,10");
//			}else{
//				sql.append(" limit "+info.getPage()*10+","+10);
//			}
			sql.append(" and user_name not in ( select top "+info.getPage()*10+" user_name from userinfo where 1 =1 "+ ssql.toString() +" order by create_time desc)");
			sql.append(" order by create_time desc");
			ResultSet rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				User user = new User();
				user.setUserName(rs.getString("user_name"));
				user.setPassword(rs.getString("password"));
				user.setPhoneNum(rs.getString("phone_number"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				Timestamp createTime = rs.getTimestamp("create_time");
				user.setCreateTime(createTime);
				if(createTime!=null){
					user.setCreateTimeStr(sdf.format((Date)createTime));
				}
				
				
				infos.add(user);
			}
			
			rs = st.executeQuery(countSql.toString());
			if(rs.next()){
				result.setCount(rs.getInt("userCount"));
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
