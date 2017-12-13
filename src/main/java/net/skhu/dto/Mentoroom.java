package net.skhu.dto;

public class Mentoroom {
   int mentoroom_id;
   String team_name;
   String team_about;
   String team_theme;
   String team_link;
   String user_name; //조인
   int mento_id;
   int person_count;

   public int getMentoroom_id() {
      return mentoroom_id;
   }
   public void setMentoroom_id(int mentoroom_id) {
      this.mentoroom_id = mentoroom_id;
   }
   public String getTeam_name() {
      return team_name;
   }
   public void setTeam_name(String team_name) {
      this.team_name = team_name;
   }
   public String getTeam_about() {
      return team_about;
   }
   public void setTeam_about(String team_about) {
      this.team_about = team_about;
   }
   public String getTeam_theme() {
      return team_theme;
   }
   public void setTeam_theme(String team_theme) {
      this.team_theme = team_theme;
   }
   public String getTeam_link() {
      return team_link;
   }
   public void setTeam_link(String team_link) {
      this.team_link = team_link;
   }
   public String getUser_name() {
	      return user_name;
   }
   public void setUser_name(String user_name) {
	  this.user_name = user_name;
   }

   public int getMento_id() {
      return mento_id;
   }
   public void setMento_id(int mento_id) {
      this.mento_id = mento_id;
   }
   public int getPerson_count() {
      return person_count;
   }
   public void setPerson_count(int person_count) {
      this.person_count = person_count;
   }



}