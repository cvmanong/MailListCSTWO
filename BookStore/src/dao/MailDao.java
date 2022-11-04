package dao;

import client.entiry.Mail;

import java.sql.*;
import java.util.ArrayList;

/**
 * Dao数据访问层
 *
 * 主要是对数据进行增删改查操作的层，也称持久层
 */
public class MailDao {

    /**
     * queryAll()方法
     * 查询所有图书信息，返回一个集合
     * @return
     */
    public ArrayList<Mail> queryAll(){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        ArrayList<Mail> list=new ArrayList<>();
        try{
            conn=DBUtil.getConnection();
            stmt=conn.createStatement();
            String sql="select*from mail order by id";
            rs=stmt.executeQuery(sql);
            while(rs.next()){
                Mail mail=new Mail();
                // 将获取结果集的信息封装到mail实体类中
                mail.setId(rs.getString("id"));
                mail.setName(rs.getString("name"));
                mail.setPrice(rs.getDouble("price"));
                mail.setNumber(rs.getInt("number"));
                mail.setAddress(rs.getString("address"));
                // 将实体类中的信息封装到集合中
                list.add(mail);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }
        return list;
    }

    /**
     * addmail()方法
     * 以实体类mail作为参数类型，实参封装了装备添加到数据库中的图书信息
     *
     * 使用PreparedStatement对象执行插入操作，防止SQL语句注入
     * @return
     */
    public void addMail(Mail mail){
        Connection conn=null;
        PreparedStatement prestmt=null;
        try{
            conn=DBUtil.getConnection();
            String sql="insert into mail(id,name,price,number,address)values(?,?,?,?,?)";
            prestmt=conn.prepareStatement(sql);
            prestmt.setString(1,mail.getId());
            prestmt.setString(2,mail.getName());
            prestmt.setDouble(3,mail.getPrice());
            prestmt.setInt(4,mail.getNumber());
            prestmt.setString(5,mail.getAddress());
            int num=prestmt.executeUpdate();
            if(num>0){
                System.out.println("插入数据成功！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(prestmt,conn);
        }
    }

    /**
     * deletemail方法
     * 删除记录deletemail()方法中的形参类型为String类型的id,也可改为实体类mail作为形参类型
     *
     * 使用PreparedStatement对象执行删除操作，防止SQL语句注入
     * @return
     */
    public void deleteMail(String id){
        Connection conn=null;
        PreparedStatement prestmt=null;
        try{
            conn=DBUtil.getConnection();
            String sql="delete from mail where id=?";
            prestmt=conn.prepareStatement(sql);
            prestmt.setString(1,id);
            int num=prestmt.executeUpdate();
            if(num>0){
                System.out.println("删除数据成功！");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(prestmt,conn);
        }
    }

}
