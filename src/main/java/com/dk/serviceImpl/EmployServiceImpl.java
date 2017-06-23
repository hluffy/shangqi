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

import com.dk.object.EmployInfo;
import com.dk.result.Result;
import com.dk.service.EmployService;
import com.dk.util.DBUtil;

@Service
public class EmployServiceImpl implements EmployService{

	@Override
	public Result getInfos() {
		// TODO Auto-generated method stub
		Result result = new Result();
		List<EmployInfo> infos = new ArrayList<EmployInfo>();
		Connection conn = null;
		Statement st = null;
		
		try {
			String countsql = "select count(*) as sumcount from employee";
			String sql = "select top 10 * from employee order by last_use_time desc";
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(countsql);
			
			if(rs.next()){
				result.setCount(rs.getInt("sumcount"));
			}
			
			rs = st.executeQuery(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				EmployInfo info = new EmployInfo();
				info.setEmployeeId(rs.getString("employee_id"));
				info.setEmployeeName(rs.getString("employee_name"));
				info.setEmployeeShiftGroup(rs.getString("employee_shift_group"));
				info.setEmployeeSite(rs.getString("employee_site"));
				info.setEmployeeShift(rs.getString("employee_shift"));
				Timestamp lastUseTime = rs.getTimestamp("last_use_time");
				info.setLastUseTime(lastUseTime);
				if(lastUseTime!=null){
					info.setLastUseTimeStr(sdf.format((Date)lastUseTime));
				}
				infos.add(info);
			}
			
			result.setStates(true);
			result.setMessage("查询成功");
			result.setData(infos);
		} catch (Exception e) {
			result.setStates(false);
			result.setMessage("查询失败");
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

	@Override
	public Result addInfo(EmployInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getEmployeeId()==null||info.getEmployeeId().isEmpty()
				||info.getEmployeeName()==null||info.getEmployeeName().isEmpty()){
			result.setStates(false);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "insert into employee(employee_id,employee_name,employee_shift_group,employee_site,employee_shift,last_use_time) values(?,?,?,?,?,?)";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getEmployeeId());
			ps.setString(2, info.getEmployeeName());
			ps.setString(3, info.getEmployeeShiftGroup());
			ps.setString(4, info.getEmployeeSite());
			ps.setString(5, info.getEmployeeShift());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			
			ps.execute();
			result.setStates(true);
			result.setMessage("保存成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setStates(false);
			result.setMessage("保存失败");
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
	public Result updateInfo(EmployInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		if(info.getEmployeeId()==null||info.getEmployeeId().isEmpty()
				||info.getEmployeeName()==null||info.getEmployeeName().isEmpty()){
			result.setStates(true);
			result.setMessage("参数不能为空");
			return result;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "update employee set employee_name=?,employee_shift_group=?,employee_site=?,employee_shift=?,last_use_time=? where employee_id=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getEmployeeName());
			ps.setString(2, info.getEmployeeShiftGroup());
			ps.setString(3, info.getEmployeeSite());
			ps.setString(4, info.getEmployeeShift());
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			ps.setString(6, info.getEmployeeId());
			
			ps.execute();
			result.setStates(true);
			result.setMessage("更新成功");
			
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
	public Result deleteInfo(EmployInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql= "delete from employee where employee_id = ?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getEmployeeId());
			
			ps.execute();
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
	public Result getInfo(EmployInfo info) {
		// TODO Auto-generated method stub
		Result result = new Result();
		Connection conn = null;
		Statement st = null;
		List<EmployInfo> infos = new ArrayList<EmployInfo>();
		try {
			StringBuffer sql = new StringBuffer("select top 10 * from employee where 1 =1 ");
			StringBuffer countsql = new StringBuffer("select count(*) as sumcount from employee where 1 = 1 ");
			StringBuffer ssql = new StringBuffer();
			if(info.getEmployeeId()!=null&&!info.getEmployeeId().isEmpty()){
				sql.append(" and employee_id = '"+info.getEmployeeId()+"'");
				countsql.append(" and employee_id = '"+info.getEmployeeId()+"'");
				ssql.append(" and employee_id = '"+info.getEmployeeId()+"'");
			}
			if(info.getEmployeeName()!=null&&!info.getEmployeeName().isEmpty()){
				sql.append(" and employee_name = '"+info.getEmployeeName()+"'");
				countsql.append(" and employee_name = '"+info.getEmployeeName()+"'");
				ssql.append(" and employee_name = '"+info.getEmployeeName()+"'");
			}
			if(info.getEmployeeShiftGroup()!=null&&info.getEmployeeShiftGroup().isEmpty()){
				sql.append(" and employee_shift_group = '"+info.getEmployeeShiftGroup()+"'");
				countsql.append(" and employee_shift_group = '"+info.getEmployeeShiftGroup()+"'");
				ssql.append(" and employee_shift_group = '"+info.getEmployeeShiftGroup()+"'");
			}
			if(info.getEmployeeSite()!=null&&!info.getEmployeeSite().isEmpty()){
				sql.append(" and employee_site = '"+info.getEmployeeSite()+"'");
				countsql.append(" and employee_site = '"+info.getEmployeeSite()+"'");
				ssql.append(" and employee_site = '"+info.getEmployeeSite()+"'");
			}
			if(info.getEmployeeShift()!=null&&!info.getEmployeeShift().isEmpty()){
				sql.append(" and employee_shift = '"+info.getEmployeeSite()+"'");
				countsql.append(" and employee_shift = '"+info.getEmployeeSite()+"'");
				ssql.append(" and employee_shift = '"+info.getEmployeeSite()+"'");
			}
			
			sql.append(" and employee_id not in(select top "+info.getPage()*10+" employee_id from employee  where 1 = 1 "+ssql.toString()+" order by last_use_time desc) order by last_use_time desc");
			conn = DBUtil.getConnection();
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(countsql.toString());
			
			if(rs.next()){
				result.setCount(rs.getInt("sumcount"));
			}
			
			rs = st.executeQuery(sql.toString());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			while(rs.next()){
				EmployInfo einfo = new EmployInfo();
				einfo.setEmployeeId(rs.getString("employee_id"));
				einfo.setEmployeeName(rs.getString("employee_name"));
				einfo.setEmployeeShiftGroup(rs.getString("employee_shift_group"));
				einfo.setEmployeeSite(rs.getString("employee_site"));
				einfo.setEmployeeShift(rs.getString("employee_shift"));
				Timestamp lastUseTime = rs.getTimestamp("last_use_time");
				einfo.setLastUseTime(lastUseTime);
				if(lastUseTime!=null){
					einfo.setLastUseTimeStr(sdf.format((Date)lastUseTime));
				}
				infos.add(einfo);
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
