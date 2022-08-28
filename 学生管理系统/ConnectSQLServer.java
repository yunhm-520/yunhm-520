package 学生管理系统;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

class DatebaseConnection {
    //数据库连接成功
    public final String DBDRIVER="net.sourceforge.jtds.jdbc.Driver";
    //数据库连接的URL
    public  final String DBURL="jdbc:jtds:sqlserver://127.0.0.1:1433/Competition";
    //数据库登录名
    public final String DBUSER="sa";
    //数据库登录密码
    public final String DBPASS="Ambow99999999";
    private Connection conn=null;
    public DatebaseConnection(){
        try{
            //加载数据库驱动
            Class.forName(DBDRIVER);
            //获取数据库连接
            conn=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"数据库连接失败","异常",JOptionPane.ERROR_MESSAGE );
            System.exit(0);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "驱动加载失败","异常",JOptionPane.ERROR_MESSAGE );
            System.exit(0);
        }
    }
    public Connection getConnection(){
        return this.conn;
    }
    public void close(){
        //关闭数据库连接
        if(this.conn!=null){
            try{
                this.conn.close();
            }catch(SQLException e){}
        }
    }
}
public class ConnectSQLServer{
    public static void main(String[] args) {
        try{
            Connection con=new DatebaseConnection().getConnection();
            if(con!=null){
                JOptionPane.showMessageDialog(null,"数据库连接成功","祝贺",JOptionPane.INFORMATION_MESSAGE );
                System.exit(0);
            }else{
                JOptionPane.showMessageDialog(null, "数据库连接失败","错误",JOptionPane.ERROR_MESSAGE );
                System.exit(0);
            }
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}

