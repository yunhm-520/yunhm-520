package xuesheng;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class Menu {
    private JButton but1 = new JButton("增加数据"); // 按钮
    private JButton but2 = new JButton("删除数据");
    private JButton but3 = new JButton("修改数据");
    private JButton but4 = new JButton("查看数据");
    private JButton but0 = new JButton("退出系统");
    private JButton but5 = new JButton("显示");
    private JButton clear = new JButton("清空");
    private JTextField number = new JTextField();// 文本框
    private JTextField name = new JTextField();
    private JTextField dor = new JTextField();
    private JTextField address = new JTextField();
    private JTextField sex = new JTextField();
    private JTextField date = new JTextField();
    private JTextField pol = new JTextField();
    private JTextField phone = new JTextField();

    private JTextArea show = new JTextArea(16, 30);
    private JLabel lab1 = new JLabel("姓名:");// 标签
    private JLabel lab2 = new JLabel("学院:");
    private JLabel num = new JLabel("学号:");
    private JLabel lab4 = new JLabel("性别:");
    private JLabel lab5 = new JLabel("专业班级:");
    private JLabel lab6 = new JLabel("出生日期:");
    private JLabel lab7 = new JLabel("政治面貌:");
    private JLabel lab8 = new JLabel("联系方式:");
    // private JLabel lab3 = new JLabel("请输入内容，完成操作。");

    private JFrame frame = new JFrame("信息管理系统"); // 框架
    private JFrame frame1 = new JFrame("显示信息");

    Hashtable<String, Person> has = new Hashtable<String, Person>();// 哈希表，加密，文件乱码
    File file = new File("新建文件.txt");// 注意：新建一个文件
    public Menu() {
        if (!file.exists()) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));// 把一个实例的对象以文件的形式保存到磁盘上。        out.writeObject(has);
                out.close();
            } catch (IOException e) {
            }
        }
        but1.addActionListener(new ActionListener() { // 增加，内部类//进行某项操作时触发功能
            public void actionPerformed(ActionEvent e) {//用于接收操作事件的侦听器接口
                if (e.getSource() == but1) {
                    but3.setEnabled(false);//使but3这个按钮变灰不可点击了
                    String number1 = number.getText();
                    if (number1.length() == 12) {
                        try {
                            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));//读回对象
                            has = (Hashtable) in.readObject();							                       in.close();
                        } catch (Exception e1) {
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "请输入12位数字的学号");//提示框显示
                    }
                    //
                    if (number1.length() == 12) {
                        if (has.containsKey(number1)) {						JOptionPane.showMessageDialog(null, "该生信息已存在，请到修改页面修改！");
                        } else {
                            String name1 = name.getText();
                            String dor1 = dor.getText();
                            String address1 = address.getText();
                            String sex1 = sex.getText();
                            String date1 = date.getText();
                            String pol1 = pol.getText();
                            String phone1 = phone.getText();
                            Person per = null;
                            per = new Person(number1, name1, dor1, address1, sex1, date1, pol1, phone1);
                            has.put(number1, per);// ???
                            try {
                                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                                out.writeObject(has);
                                out.close();
                                JOptionPane.showMessageDialog(null, "添加成功！");
                            } catch (Exception e1) {}
                        }
                    }
                }
            }
        });
        clear.addActionListener(new ActionListener() { // 清空
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == clear) {//获得事件所作用的对象

                    number.setText(null);
                    dor.setText(null);
                    name.setText(null);
                    address.setText(null);
                    sex.setText(null);
                    date.setText(null);
                    pol.setText(null);
                    phone.setText(null);
                }
            }
        });
        but2.addActionListener(new ActionListener() { // 删除
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == but2) {
                    but3.setEnabled(false);
                    String number1 = number.getText();
                    if (number1.length() == 12) {
                        try {
                            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                            has = (Hashtable) in.readObject();
                            in.close();
                        } catch (Exception e1) {
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "请输入12位数字的学号");
                    }
                    if (has.containsKey(number1)) {
                        has.remove(number1);
                        ObjectOutputStream out = null;
                        JOptionPane.showMessageDialog(null, "删除成功");
                        try {
                            out = new ObjectOutputStream(new FileOutputStream(file));
                            //out.writeObject(has);
                            out.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);//记录当前类可能发生的异常
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "学号不存在");
                    }
                }
            }
        });

        but3.addActionListener(new ActionListener() { // 修改
            public void actionPerformed(ActionEvent e) {
                //String number2 = number.getText();
                if (e.getSource() == but3) {
                    but3.setEnabled(false);

                    String number1 = number.getText();
                    String name1 = name.getText();
                    String dor1 = dor.getText();
                    String address1 = address.getText();
                    String sex1 = sex.getText();
                    String date1 = date.getText();
                    String pol1 = pol.getText();
                    String phone1 = phone.getText();
                    Person per = new Person(number1, name1, dor1, address1, sex1, date1, pol1, phone1);
                    has.put(number1, per);
                    if (number1.length() == 12) {
                        try {
                            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                            has = (Hashtable) in.readObject();
                            in.close();
                        } catch (Exception e1) {
                        }
                        JOptionPane.showMessageDialog(null, "修改成功");

                        try {
                            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
                            out.writeObject(has);
                            out.close();
                        } catch (Exception e1) {
                        }
                    }
                }
            }});

        but4.addActionListener(new ActionListener() { // 查看
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == but4) {
                    but3.setEnabled(false);
                    String number1 = number.getText();
                    if (number1.length() == 12) {
                        if (has.containsKey(number1)) {
                            try {
                                ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                                has = (Hashtable) in.readObject();
                                in.close();
                            } catch (Exception e1) {
                            }
                            Person per = (Person) has.get(number1);
                            name.setText(per.getName());
                            dor.setText(per.getDor());
                            address.setText(per.getAddress());
                            sex.setText(per.getSex());
                            date.setText(per.getDate());
                            pol.setText(per.getPol());
                            phone.setText(per.getPhone());

                            but3.setEnabled(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "学号不存在");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "请输入12位数字的学号");
                    }
                }
            }

        });
        but5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == but5) {
                    frame1.setVisible(true);
                    try {
                        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                        has = (Hashtable) in.readObject();
                        in.close();
                    } catch (Exception ee) {
                    }
                    if (has.isEmpty()) {
                        show.append("目前还没有学生的信息记录！\n");
// append(s:String)向文本域的文本追加字符串，简单的说就像system.out.println()
                    } else {

                        for (Enumeration enu = has.elements(); enu.hasMoreElements();) {
//存入内存的内容如果不经过遍历是显示不出来的
                            Person per = (Person) enu.nextElement();
                            String str = "  <学号>：" + per.getNum() + "\n" + "  <姓名>：" + per.getName() + "\n" + "  <学院>："
                                    + per.getDor() + "\n" + "  <性别>：" + per.getAddress() + "\n" + "  <专业班级>："
                                    + per.getSex() + "\n" + "<出生日期>：" + per.getDate() + "\n" + "  <政治面貌>："
                                    + per.getPol() + "\n" + " <电话>：" + per.getPhone() + "\n" + "\n";
                            show.append(str);
                        }
                        String str2 = "------------------------------结束---------------------------------------------------"
                                + "\n";
                        show.append(str2);
                    }
                }
            }

        });
        but0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == but0) {
                    frame.setVisible(false);
                    new ActionHandle();
                }
            }
        });
        frame.setLayout(null);
        but1.setBounds(30, 35, 90, 25);
        but2.setBounds(30, 75, 90, 25);
        but3.setBounds(30, 115, 90, 25);
        but4.setBounds(30, 155, 90, 25);
        but0.setBounds(240, 430, 100, 25); // setBounds(x,y,width,height);
        num.setBounds(150, 30, 70, 25);
        lab1.setBounds(150, 65, 70, 25);
        lab2.setBounds(150, 100, 70, 25);//
        lab4.setBounds(150, 135, 70, 25);
        lab5.setBounds(150, 170, 70, 25);
        lab6.setBounds(150, 205, 70, 25);
        lab7.setBounds(150, 240, 70, 25);
        lab8.setBounds(150, 275, 70, 25);
        number.setBounds(230, 30, 90, 25);
        name.setBounds(230, 65, 90, 25);
        dor.setBounds(230, 100, 90, 25);
        address.setBounds(230, 135, 90, 25);
        sex.setBounds(230, 170, 90, 25);
        date.setBounds(230, 205, 90, 25);
        pol.setBounds(230, 240, 90, 25);
        phone.setBounds(230, 275, 90, 25);
        // lab3.setBounds(130, 390, 250, 25);
        clear.setBounds(250, 310, 60, 25);
        but5.setBounds(150, 310, 60, 25);
        frame.add(lab1);
        frame.add(lab2);
        //frame.add(lab3);
        frame.add(lab4);
        frame.add(lab5);
        frame.add(lab6);
        frame.add(lab7);
        frame.add(lab8);
        frame.add(num);
        frame.add(number);
        frame.add(name);
        frame.add(dor);
        frame.add(address);
        frame.add(sex);
        frame.add(date);
        frame.add(pol);
        frame.add(phone);
        frame.add(clear);
        frame.add(but1);
        frame.add(but2);
        frame.add(but3);
        frame.add(but4);
        frame.add(but0);
        JScrollPane scroll = new JScrollPane(show);
        frame1.add(scroll,BorderLayout.CENTER);
        frame.add(but5);
        frame.setSize(400, 500); // 页面大小
        frame1.setBounds(200, 200, 400, 300);
        frame.setLocation(300, 200);
        frame.setVisible(true);
        frame1.setVisible(false);
    }

    public static void main(String[] args) {

        new ActionHandle();
    }
}




