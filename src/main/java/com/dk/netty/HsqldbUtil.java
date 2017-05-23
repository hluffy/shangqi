package com.dk.netty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.dk.object.IbeaconInfo;
import com.dk.object.LocalizerInfo;
import com.dk.util.DBUtil;


public class HsqldbUtil {
	public static void main(String[] args) throws Exception {
		Connection connect = DBUtil.getConnection();
		System.out.println(connect);
	}
	
	public static void updateLocalizerArea(LocalizerInfo info){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "update localizer set area=? where number=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getArea());
			ps.setString(2, info.getNumber());
			ps.execute();
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
	}
	
	public static void updateLocalizer(LocalizerInfo info){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "update localizer set static_time=?,run_time=?,time=? where number=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, info.getStaticTime());
			ps.setString(2, info.getRunTime());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.setString(4, info.getNumber());
			ps.execute();
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
		
	}
	
	//添加定位信息
	public static void addPositioning(Positioning positioning){
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into positioning(equipment_num,longitude,latitude,positioning_mode,positioning_time,area,electricity) values(?,?,?,?,?,?,?)";
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, positioning.getEquipmentNum());
			ps.setDouble(2, positioning.getLongitude());
			ps.setDouble(3, positioning.getLatitude());
			ps.setString(4, positioning.getPositioningMode());
			ps.setTimestamp(5, positioning.getPositioningTime());
			ps.setString(6, positioning.getArea());
			ps.setInt(7, positioning.getElectricity());
			
			ps.execute();
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
	}
	
	//更新定位信息
	public static void updatePositioning(Positioning positioning){
		Connection connect = null;
		PreparedStatement ps = null;
		String sql = "update positioning set equipment_num=?,longitude=?,latitude=?,positioning_mode=?,positioning_time=? where equipment_num=?";
		try {
			connect = DBUtil.getConnection();
			ps = connect.prepareStatement(sql);
			ps.setString(1, positioning.getEquipmentNum());
			ps.setDouble(2, positioning.getLongitude());
			ps.setDouble(3, positioning.getLatitude());
			ps.setString(4, positioning.getPositioningMode());
			ps.setTimestamp(5, positioning.getPositioningTime());
			ps.setString(6, positioning.getEquipmentNum());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connect!=null){
				try {
					connect.close();
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
	}
	
	public static void updateIbeacon(IbeaconInfo info){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String sql = "update ibeacon set ele=?,last_use_time=? where uuid=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, info.getEle());
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setString(3, info.getUuid());
			ps.execute();
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
	}
	
	//获得ibeacon信息
	public static IBeacon getIbeacon(String uuid){
		IBeacon ibeacon = new IBeacon();
		Connection connect = null;
		PreparedStatement ps = null;
		String sql = "select * from IBeacon where uuid = ?";
		try {
			connect = DBUtil.getConnection();
			ps = connect.prepareStatement(sql);
			ps.setString(1, uuid);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				ibeacon.setUuid(uuid);
				ibeacon.setLongitude(rs.getDouble("longitude"));
				ibeacon.setLatitude(rs.getDouble("latitude"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ibeacon;
	}
	
//	public static Connection getConnection(){
//		Connection connect = null;
//		try {
//			Class.forName("org.hsqldb.jdbcDriver");
//			connect = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/","sa","");
////			connect = DriverManager.getConnection("jdbc:hsqldb:file:D:/apache-tomcat-7.0.55/lib/hsqldb/data/test","sa","");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return connect;
//	}

}
