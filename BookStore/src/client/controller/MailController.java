package client.controller;

import client.entiry.Mail;
import client.service.MailService;
import client.view.MailAdmin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;

/**
 * mailController表示层
 *
 * 对数据库的访问通过业务逻辑层的方法调用来实现
 */
public class MailController extends MailAdmin {

    // 创建mailService实例化对象
    private MailService mailService=new MailService();

    public MailController() {
        queryAll();
    }

    // 将查询的数据转换成二维数组的格式，作为表格中表体的内容
    private String[][] list2Array(ArrayList<Mail> list){
        String[][] body=new String[list.size()][4];
        for(int i=0;i<list.size();i++){
            Mail mail=list.get(i);
            body[i][0]=mail.getId();
            body[i][1]=mail.getName();
            // valueOf()方法返回非字符串类型数据的字符串表现形式
            body[i][2]= String.valueOf(mail.getAddress());
            body[i][3]= String.valueOf(mail.getNumber());
        }
        return body;
    }

    @Override
    public void queryAll() {
        String[] head={"编号","姓名","地址","电话"};
        // 将queryAll()方法返回的数据封装到集合中
        ArrayList<Mail> list=mailService.queryAll();
        // 定义一个String类型的二维数组存储list2Array()方法返回的二维数组，这个二位数组就是表格的表体内容
        String[][] body=list2Array(list);
        TableModel tableModel=new DefaultTableModel(body,head);
        table.setModel(tableModel);
    }

    @Override
    public void addMail() {
        String id=addIdText.getText();
        String name=addNameText.getText();
        String address=addPriceText.getText();
        String number=addNumberText.getText();
        boolean addSuccess=mailService.addMail(id,name,0,Integer.parseInt(number),address);
        if(addSuccess){
            queryAll();
        }else{
            JOptionPane.showMessageDialog(this,"已存在！");
        }
    }

    @Override
    public void updateMail() {
        String id=updateIdText.getText();
        String name=updateNameText.getText();
        String address=updatePriceText.getText();
        String number=updateNumberText.getText();
        boolean updateSuccess=mailService.updateMail(id,name,0,Integer.parseInt(number),address);
        if(updateSuccess){
            queryAll();
        }else{
            JOptionPane.showMessageDialog(this,"不存在！");
        }
    }

    @Override
    public void deleteMail() {
        String id=delIdText.getText();
        boolean deleteSuccess=mailService.deleteMail(id);
        if(deleteSuccess){
            queryAll();
        }else{
            JOptionPane.showMessageDialog(this,"不存在！");
        }
    }


}
