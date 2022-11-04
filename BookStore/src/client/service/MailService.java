package client.service;

import dao.MailDao;
import client.entiry.Mail;

import java.util.ArrayList;

/**
 * Service业务逻辑层
 *
 * 主要是调用Dao数据访问层的基本数据操作来完成业务逻辑处理
 */
public class MailService {

    // 创建AdminDao实例化对象
    private MailDao mailDao=new MailDao();

    // 实现查询逻辑处理
    public ArrayList<Mail> queryAll(){
        ArrayList data=mailDao.queryAll();
        return data;
    }

    // 实现添加逻辑处理
    public boolean addMail(String id, String name, double price, int number,String address){
        // 遍历数据，判断要插入的水果编号是否存在
        ArrayList<Mail> data=queryAll();
        for(int i=0;i<data.size();i++){
            Mail mail=data.get(i);
            if(id.equals(mail.getId())){
                return false;
            }
        }
        Mail thismail=new Mail(id,name,price,number,address);
        mailDao.addMail(thismail);
        return true;
    }

    /**
     * mailDao中没有定义修改操作的方法，我们这个小项目中不是通过执行修改SQL语句来实现数据修改的，
     * 而是通过删除要修改的数据后，再添加新的数据的方式来实现数据修改的
     * @return
     */
    public boolean updateMail(String id, String name, double price, int number,String address) {
        // 遍历数据，判断要插入的水果编号是否存在
        ArrayList<Mail> data=queryAll();
        for(int i=0;i<data.size();i++){
            Mail mail=data.get(i);
            if(id.equals(mail.getId())){
                // 如果该图书存在则删除
                mailDao.deleteMail(id);
                // 添加修改内容作为新的图书内容
                Mail thismail=new Mail(id,name,price,number,address);
                mailDao.addMail(thismail);
                /**
                 * 这里不要使用mailService类中的deletemail()和addmail()方法来删除添加图书，
                 * 因为这两个方法都要遍历一次数据，如果数据量很大的话就会消耗很多时间，
                 * 所以应如上面三行代码这样，直接调用mailDao类中的方法来实现图书的删除添加
                 */
//                deletemail(id);
//                addmail(id,name,price,number);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMail(String id) {
        // 遍历数据，判断要插入的水果编号是否存在
        ArrayList<Mail> data=queryAll();
        for(int i=0;i<data.size();i++){
            Mail mail=data.get(i);
            if(id.equals(mail.getId())){
                mailDao.deleteMail(id);
                return true;
            }
        }
        return false;
    }



}
