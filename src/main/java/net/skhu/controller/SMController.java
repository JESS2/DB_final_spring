package net.skhu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import net.skhu.dto.Menti;
import net.skhu.dto.Mentoroom;
import net.skhu.dto.Uploadfile;
import net.skhu.dto.User;
import net.skhu.mapper.MentiMapper;
import net.skhu.mapper.MentoroomMapper;
import net.skhu.mapper.UploadfileMapper;
import net.skhu.mapper.UserMapper;

@RestController
@RequestMapping("api/")
public class SMController {

   @Autowired UserMapper userMapper;
   @Autowired MentoroomMapper mentoroomMapper;
   @Autowired MentiMapper mentiMapper;
   @Autowired UploadfileMapper uploadfileMapper;

   // 로그인
   @RequestMapping(value = "user_login", method = RequestMethod.POST)
   public Map<String, Object> login(Model model, @RequestBody User user, HttpServletRequest request)
            throws UnsupportedEncodingException {

         User db_user = userMapper.selectByUserId(user.getUser_id());
         HashMap<String, Object> map = new HashMap<>();
         if (user.getUser_password().equals(db_user.getUser_password())) {
               map.put("title", "로그인 되었습니다");
               map.put("key", 0);
               map.put("user_id", db_user.getUser_id());
               map.put("user_auth", db_user.getUser_auth());
               return map;
            }
            map.put("title", "비밀번호가 맞지 않습니다");
            map.put("key", 2);
            return map;
   }

   //회원 목록
    @RequestMapping("user_list")
    public List<User> user_list(Model model, HttpServletRequest request) {
       List<User> list = userMapper.findAll();
        return list;
    }

    //엑셀 등록
    @RequestMapping(value="excel", method = RequestMethod.POST)
    public void execel(@RequestBody User[] user, Model model,HttpServletRequest request) {
      for(int i=0; i<user.length; i++)
         userMapper.insert(user[i]);
    }

    //멘토방 목록
     @RequestMapping("mentoroom_list")
     public List<Mentoroom> mentoroomList(HttpServletRequest request, Model model) throws UnsupportedEncodingException, IllegalArgumentException, IllegalAccessException {

    	 return mentoroomMapper.findAll();

     }

   //멘토신청
//     @RequestMapping(value = "mento/create", method = RequestMethod.POST)
//     public int mentoroom_create(@RequestBody Mentoroom mentoroom, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
//        mentoroomMapper.insert(mentoroom);
//        User user = new User();
//        user.setUser_auth(1);
//        user.setUser_id(mentoroom.getMento_id());
//        userMapper.updateUserauth(user);
//
//        return mentoroom.getMentoroom_id();
//     }

   //멘토신청 (파일업로드 제외)
     @RequestMapping(value = "mento/create", method = RequestMethod.POST)
     public void mentoroom_create(@RequestBody Mentoroom mentoroom, Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        mentoroomMapper.insert(mentoroom);
        User user = new User();
        user.setUser_auth(1);
        user.setUser_id(mentoroom.getMento_id());
        userMapper.updateUserauth(user);
     }


   //멘티신청
  	@RequestMapping(value="mentoroom/{mid}/{uid}/mentijoin")
  	public void menti_join(Model model, HttpServletRequest request, @PathVariable("mid") int mid, @PathVariable("uid") int uid) {
  		User user = new User();
  		user.setUser_auth(2);
  		user.setUser_id(uid);
  		userMapper.updateUserauth(user);
  		Menti menti = new Menti();
  		menti.setMenti_id(uid);
  		menti.setMento_id(mid);
  		mentiMapper.insert(menti);
  		mentoroomMapper.updatePersoncount(mid);
  	}

  //파일 업로드
    @Transactional
    @RequestMapping(value="mentoroom/fileupload/{r_id}/{kind}", method = RequestMethod.POST)
    public void fileupload(@RequestBody MultipartFile uploadFile, @PathVariable("kind") int kind, MultipartHttpServletRequest mrequest, Model model,HttpServletRequest request, @PathVariable("r_id") int r_id ) throws IllegalStateException, IOException {
       String file_name = Paths.get(uploadFile.getOriginalFilename()).getFileName().toString();
       Uploadfile uf = new Uploadfile();
       uf.setFile_name(file_name);
       uf.setFile_data(uploadFile.getBytes());
       uf.setFile_type(uploadFile.getContentType());
       uf.setFile_kind(kind);
       uf.setMentoroom_id(r_id);
       uploadfileMapper.insert(uf);
    }

    //보고서, 사진 목록
    @RequestMapping(value="mentoroom/filelist/{r_id}")
    public List<Uploadfile> file_list(Model model, HttpServletRequest request, @PathVariable("r_id") int r_id) {
        return uploadfileMapper.findByRoomId(r_id);
    }

    //파일 삭제
    @RequestMapping(value="mentoroom/filedelete/{f_id}")
    public String filedelete(@PathVariable("f_id") int f_id, HttpServletRequest request, Model model ) throws UnsupportedEncodingException {
       uploadfileMapper.delete(f_id);
       return "파일이 삭제되었습니다";
   }

  //멘티목록
  		@RequestMapping(value="mentoroom/{m_id}/menti_list")
  		public List<Menti> menti_list(Model model, HttpServletRequest request, @PathVariable("m_id") int m_id) {
  		return mentiMapper.findAll(m_id);
  		}


}




