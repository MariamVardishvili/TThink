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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes("player")
public class GameController {
    @Autowired
    private RoomService roomService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private ImageService imgService;
    @Autowired
    private PlayerCardsService playerCardsService;

    @RequestMapping("/")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @RequestMapping(value = "/newRoom")
    public ModelAndView newRoom(@RequestParam("userName") String userName, ModelMap modelMap) {
        Player currentPlayer=  roomService.createNewRoom(userName);
        modelMap.put("player", currentPlayer);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/lobby");
        return modelAndView;
    }

    @RequestMapping(value = "/joinRoom")
    public RedirectView joinRoom(@RequestParam("userName") String userName, @RequestParam("roomNumber") int roomNumber, ModelMap modelMap) {
        Player currentPlayer = playerService.connectPlayerAndRoom(userName, roomNumber, false);
        if(currentPlayer!=null) {
            modelMap.put("player", currentPlayer);
            RedirectView view = new RedirectView("/lobby");
            return view;
        }
        RedirectView r = new RedirectView( "/myerror");
        return r;
    }

    @RequestMapping("/lobby")
    public ModelAndView lobby(ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("lobby.html");
        return modelAndView;
    }

    @RequestMapping(value ="/checkMembers", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Player> checkMembers(ModelMap modelMap) {
        Player currentPlayer = (Player) modelMap.get("player");
        List<Player> activePlayersFromRoom = playerService.getActivePlayersFromRoom(currentPlayer.getRoom().getRoomNumber());
        for (Player p:activePlayersFromRoom) {
            System.out.println("HERE "+ currentPlayer.getName() +" "+ p.getRoom().getStarted());
        }

        return activePlayersFromRoom;
    }
    @RequestMapping("/startGame")
    public RedirectView startGame(ModelMap modelMap) {
        Player currentPlayer = (Player) modelMap.get("player");
        roomService.startGame(currentPlayer.getRoom());
        RedirectView view = new RedirectView("/playerHand");
        return  view;
    }

    @RequestMapping("/playerHand")
    public ModelAndView personalHand(ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        Player currentPlayer = (Player) modelMap.get("player");

        List<PlayedImages> playerImages = playerCardsService.top6ActiveCardsByUser(currentPlayer);
       // Player playerForCurrentRound = playerService.getCurrentPlayer(currentPlayer.getRoom());
        List<Player> playersInOrder = playerService.getPlayersInOrder(currentPlayer.getRoom());
        Player playerForRound = playerService.getCurrentPlayer(currentPlayer.getRoom());
        modelAndView.addObject("playerCardss", playerImages);
        modelAndView.addObject("playerForThisRound", playerForRound);

        modelAndView.addObject("playersInOrder", playersInOrder);
        modelAndView.setViewName("playerCards.html");

        return modelAndView;
    }
}
