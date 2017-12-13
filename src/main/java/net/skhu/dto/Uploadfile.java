package net.skhu.dto;

public class Uploadfile {

   int id;
   String file_name;
   byte[] file_data;
   String file_type;
   int mentoroom_id;
   int file_kind;

   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
   public String getFile_name() {
      return file_name;
   }
   public void setFile_name(String file_name) {
      this.file_name = file_name;
   }
   public int getFile_kind() {
      return file_kind;
   }
   public void setFile_kind(int file_kind) {
      this.file_kind = file_kind;
   }
   public int getMentoroom_id() {
      return mentoroom_id;
   }
   public void setMentoroom_id(int mentoroom_id) {
      this.mentoroom_id = mentoroom_id;
   }
   public String getFile_type() {
      return file_type;
   }
   public void setFile_type(String file_type) {
      this.file_type = file_type;
   }
   public byte[] getFile_data() {
      return file_data;
   }
   public void setFile_data(byte[] file_data) {
      this.file_data = file_data;
   }

}