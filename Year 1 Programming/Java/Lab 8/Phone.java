/** Phone class
 *  represents a phone listing
 *  @author CS1027 for Lab
 *  @param <T>
 */

public class Phone<T> implements Comparable<T>{
  
  private String name;
  private String phone;
    
  public Phone(){
    name = "";
    phone = "";
  }
  public Phone(String name, String phone){
    this.name = name;
    this.phone = phone;
  }
  public String getName(){
    return name;
  }
  public String getPhone(){
    return phone;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setPhone(String phone){
    this.phone = phone;
  }
  public String toString(){
    return (name + " " + phone);
  }
  public boolean equals(Phone other)
  {
	  return (name == other.name)&&(phone == other.phone);
	  
  }
  public int compareTo(T otherPhone){
	  Phone phoneObj = (Phone)otherPhone;
	  return this.phone.compareTo(phoneObj.phone);
  }

}
