package com.example.demo.controller;

import com.example.demo.models.GameRoom;
import com.example.demo.models.PlayedImages;
import com.example.demo.models.Player;
import com.example.demo.service.ImageService;
import com.example.demo.service.PlayerCardsService;
import com.example.demo.service.PlayerService;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.text.html.Option;
import java.nio.file.ClosedFileSystemException;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("player")
public class ClientController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ImageService imgService;
    @Autowired
    private PlayerCardsService playerCardsService;

    /*@RequestMapping("/")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index1.html");
        return modelAndView;
    }*/

    @RequestMapping("/activeRooms")
    public ModelAndView activeRooms() {
        List<GameRoom> activeRooms = roomService.getActiveRooms();
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("rooms");
        modelAndView.addObject("roomList", activeRooms);
        return modelAndView;
    }

    @RequestMapping("/activeRooms/{roomNumber}")
    public ModelAndView activeRooms(@PathVariable int roomNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rooms.html");
        roomService.getActiveRoom(roomNumber);
        return modelAndView;
    }

    /*@RequestMapping(value = "/joinRoom")
    public RedirectView joinRoom(@RequestParam("userName") String userName, @RequestParam("roomNumber") int roomNumber, ModelMap modelMap,  RedirectAttributes atts) {

        Player currentPlayer = playerService.connectPlayerAndRoom(userName, roomNumber);
        modelMap.put("player", userName);
        if(currentPlayer!=null) {
            RedirectView view = new RedirectView("/lobby/" + roomNumber);
            atts.addFlashAttribute("CurrentPlayer", currentPlayer);
            return view;
        }
        RedirectView r = new RedirectView( "/myerror");

        return r;
    }


    @RequestMapping("/lobby/{roomNumber}")
    public ModelAndView lobby(@PathVariable int roomNumber, @ModelAttribute("CurrentPlayer") final Player currentPlayer, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        String nams = (String) modelMap.get("player");
        System.out.println(nams+"------------------------------------");
        Optional<GameRoom> activeRoom = roomService.getActiveRooms(roomNumber);
        activeRoom.get().getPlayers().size();
        modelAndView.setViewName("lobby.html");
        modelAndView.addObject("roomInfo", activeRoom.get());
        modelAndView.addObject("CurrentPlayer", currentPlayer);
        return modelAndView;
    }

    @RequestMapping(value ="/checkMembers/{roomNumber}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public  List<Player> checkMembers(@PathVariable int roomNumber, ModelMap modelMap) {
        String nams = (String) modelMap.get("player");
        System.out.println(nams+"=====================================");
        List<Player> activePlayersFromRoom = playerService.getActivePlayersFromRoom(roomNumber);
        String a = "";
        for(Player pl : activePlayersFromRoom){
            System.out.println(pl.toString());
            a+=pl.getName();
        }
        return activePlayersFromRoom;
    }

    @RequestMapping(value = "/newRoom")
    public ModelAndView newRoom(@RequestParam("userName") String userName) {
        int roomNumber =  roomService.createNewRoom(userName);
        ModelAndView modelAndView = new ModelAndView();
       // modelAndView.addObject("roomNumber", roomNumber);
        modelAndView.setViewName("redirect:/lobby/"+roomNumber);
        return modelAndView;
    }*/

    @RequestMapping(value = "/test/{roomNumber}")
    @ResponseBody
    public Optional<GameRoom>  test(@PathVariable int roomNumber) {
        Optional<GameRoom> activeRooms = roomService.getActiveRoom(roomNumber);
        activeRooms.get().getPlayers().size();
        System.out.println();
        return activeRooms;
    }


    @RequestMapping(value = "/myerror")
    public ModelAndView myerror() {
        System.out.println("here hererer I am jereeeeeeeee");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        modelAndView.addObject("ErMessage", "Room not Found");

        return modelAndView;
    }
/*
    @RequestMapping("/startGame/{roomNumber}/{player}")
    public RedirectView startGame(@PathVariable int roomNumber, @PathVariable long player, RedirectAttributes atts) {
        RedirectView view = new RedirectView("/playerHand");
        atts.addFlashAttribute("CurrentPlayer", player);
        return  view;
    }


    @RequestMapping("/playerHand")
    public ModelAndView personalHand(@ModelAttribute("CurrentPlayer") final long currentPlayer) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("MY CURRENT PLAYER: "+currentPlayer);
        List<PlayedImages> playerImages = playerCardsService.getOrCreateImagesForPlayer(currentPlayer);
        System.out.println("PLayerImages; " + playerImages.get(1).toString() );
        modelAndView.addObject("playerCards", playerImages);

        modelAndView.setViewName("playerCards.html");

        return modelAndView;
    }
*/
}