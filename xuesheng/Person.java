package xuesheng;

import java.io.Serializable;
public class Person implements Serializable {//使用Serializable类，可以使对象被序列化，用于持久化（保存）对象。
    private String num;
    private String name;
    private String dor;
    private String address;
    private String sex;
    private String date;
    private String pol;
    private String phone;
    //public Person(){}
    public Person(String num,String name,String dor,String address,String sex,String date,String pol,String phone ){
        this.num=num;
        this.name=name;
        this.dor=dor;
        this.address=address;
        this.sex=sex;
        this.date=date;
        this.pol=pol;
        this.phone=phone;
    }
    public void setNum(String num){
        this.num=num;
    }
    public String getNum(){
        return num;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setDor(String dor){
        this.dor=dor;
    }
    public String getDor(){
        return dor;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress(){
        return address;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public String getSex(){
        return sex;
    }
    public void setDate(String date){
        this.date=date;
    }
    public String getDate(){
        return date;
    }
    public void setPol(String pol){
        this.pol=pol;
    }
    public String getPol(){
        return pol;
    }
    public void setPhone(String phone){
        this.phone=phone;
    }
    public String getPhone(){
        return phone;
    }

    public static void main(String[] args) {

        new ActionHandle();
    }
}





