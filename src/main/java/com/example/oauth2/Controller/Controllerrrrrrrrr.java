package com.example.oauth2.Controller;

import com.example.oauth2.DTO.RenterDTO;
import com.example.oauth2.DTO.RoomDTO;
import com.example.oauth2.DTO.RoomHistoryDTO;
import com.example.oauth2.Entity.*;
import com.example.oauth2.Repository.*;

import com.example.oauth2.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class Controllerrrrrrrrr {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoomService roomService;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RenterRepository renterRepository;
    @Autowired
    RoomHistoryRepository roomHistoryRepository;
    @GetMapping("/index")
    @Secured("ROLE_ADMIN")
    public String redirectIndex(Model model, HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        return "index";
    }
    @GetMapping("/roomIndex")
    @Secured("ROLE_ADMIN")
    public String redirectList(Model model){
        List<RoomDTO> getAllRoom  =roomService.getAllRoom();
        model.addAttribute("listRoom", getAllRoom);
        model.addAttribute("dto", new RoomDTO());

        return "roomIndex";
    }
    @PostMapping("/createOrUpdateRoom")
    @Secured("ROLE_ADMIN")
    public String createOrUpdateRoom(RoomDTO roomDTO) {
        RoomEntity room = null;
        if(roomDTO.getId() != null){
            room = roomRepository.getById(roomDTO.getId());
        }
        if(room == null){
            room = new RoomEntity();
        }
        room.setCode(roomDTO.getCode());
        room.setName(roomDTO.getName());
        room.setPrice(roomDTO.getPrice());
        roomRepository.save(room);
        return "redirect:/roomIndex";
    }
    @GetMapping("/updateRoom/")
    @Secured("ROLE_ADMIN")
    public String updateRoom(@RequestParam(value = "id", required = false) Long id, Model model) {
        RoomEntity roomEntity = roomRepository.getById(id);
        model.addAttribute("dto", new RoomDTO(roomEntity, true));
        model.addAttribute("obj",new RoomDTO());
        return "roomUpdate";
    }
    @GetMapping("/deleteRoom/")
    @Secured("ROLE_ADMIN")
    public String deleteRoom(@RequestParam(value = "id", required = false) Long id) {
        RenterEntity renter = renterRepository.getByRoomId(id);
        if(renter != null){
            return "redirect:/roomIndex";
        }
        else {
            roomRepository.deleteById(id);
            return "redirect:/roomIndex";
        }
    }

    @GetMapping("/orderRoom/")
    @Secured("ROLE_ADMIN")
    public String orderRoom(@RequestParam(value = "id", required = false) Long idRoom, Model model) {
        RoomEntity room = roomRepository.getById(idRoom);
        RenterDTO renterDTO = new RenterDTO(room);
        model.addAttribute("dto", renterDTO);
        return "orderRoom";
    }
    @PostMapping("/orderRoomRoom")
    @Secured("ROLE_ADMIN")
    public String orderRoomRoom(RenterDTO renterDTO) throws ParseException {
        RoomEntity room = roomRepository.getById(renterDTO.getRoomId());
        RenterEntity renter = new RenterEntity();
        renter.setRoom(room);
        renter.setNameCustomer(renterDTO.getNameCustomer());
        renter.setAddressCustomer(renterDTO.getAddressCustomer());
        renter.setAgeCustomer(renterDTO.getAgeCustomer());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dateStart = df.parse(renterDTO.getTimeStart());
        Date dateEnd = df.parse(renterDTO.getTimeEnd());
        renter.setTimeStart(dateStart);
        renter.setTimeEnd(dateEnd);
        renterRepository.save(renter);
        return "redirect:/roomIndex";
    }
    @Transactional
    @GetMapping("/checkoutRoom/")
    @Secured("ROLE_ADMIN")
    public String checkoutRoom(@RequestParam(value = "id", required = false) Long idRoom, Model model) throws ParseException {
        RoomEntity room = roomRepository.getById(idRoom);
        RenterEntity renter = renterRepository.getByRoomId(room.getId());
        RoomHistory roomHistory = new RoomHistory();
        roomHistory.setRoomCode(room.getCode());
        roomHistory.setTimeEnd(renter.getTimeEnd());
        roomHistory.setTimeStart(renter.getTimeStart());
        roomHistory.setTimeCheckout(new Date());
        roomHistory.setNameCustomer(renter.getNameCustomer());
        roomHistory.setAgeCustomer(renter.getAgeCustomer());
        roomHistory.setAddressCustomer(renter.getAddressCustomer());
        if (renter.getTimeStart() != null && renter.getTimeEnd() != null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate end = renter.getTimeEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate start = renter.getTimeStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            long totalDay = ChronoUnit.DAYS.between(start,end);
            Double totalPrice = room.getPrice() * totalDay;
            roomHistory.setTotalPrice(totalPrice);
        }
        roomHistoryRepository.save(roomHistory);
        renterRepository.delete(renter);
        return "redirect:/roomIndex";
    }
    @GetMapping("/getRoomHistory")
    @Secured("ROLE_ADMIN")
    public String getRoomHistory(Model model) {
        List<RoomHistoryDTO> roomHistoryDTOS = roomHistoryRepository.findAll().stream().map(x->new RoomHistoryDTO(x)).collect(Collectors.toList());
        model.addAttribute("listRoom", roomHistoryDTOS);
        model.addAttribute("totalPrice", roomHistoryRepository.totalPrice().longValue());
        return "roomHistory";
    }

    @GetMapping("/add")
    public String addUser(){
        RoleEntity r = roleRepository.findById(1L).get();
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(r);
        userRepository.save(new UserEntity("admin",bCryptPasswordEncoder.encode("admin"), roles,"Nguyen Huu Quyen"));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/403")
    public String zxczxcz403(){
        return "403";
    }



}
