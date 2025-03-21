package org.example.controller;

import jakarta.websocket.server.PathParam;
import org.example.controller.model.DateRangeDTO;
import org.example.controller.model.SinglePaymentDTO;
import org.example.exception.UserNotFoundException;
import org.example.service.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/loadData")
public class LoadDataController {

    @Autowired
    private LoadDataService loadDataService;

//    @RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<SinglePaymentDTO>> loadData(@RequestBody DateRangeDTO dateRangeDTO, @CookieValue(value = "SESSION_STRING", required = false) String sessionString) throws UserNotFoundException {
//
//        List<SinglePaymentDTO> result = loadDataService.getPaymentsBetween(dateRangeDTO.getStartDate(), dateRangeDTO.getEndDate());
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(result);
//    }
//    @RequestMapping(value = "/transactions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<SinglePaymentDTO>> loadData(@RequestBody DateRangeDTO dateRangeDTO, @CookieValue(value = "SESSION_STRING", required = true) String sessionString) throws UserNotFoundException {
//        List<SinglePaymentDTO> result = loadDataService.getPaymentsBetween(dateRangeDTO.getStartDate(), dateRangeDTO.getEndDate());
//    }

    @RequestMapping(value = "/getpaymentsbydate/{date}", method = RequestMethod.GET)
    public ResponseEntity<List<SinglePaymentDTO>> getPaymentsByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate date, @CookieValue(value = "SESSION_STRING", required = true) String sessionString) throws UserNotFoundException {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loadDataService.getPaymentsByDate(date, sessionString));
    }

    @RequestMapping(value = "/last-3-transactions", method = RequestMethod.GET)
    public ResponseEntity<List<SinglePaymentDTO>> getLast3Transactions(@CookieValue(value = "SESSION_STRING", required = true) String sessionString) throws UserNotFoundException {
        List<SinglePaymentDTO> result = loadDataService.getLastTransactions(sessionString);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public ResponseEntity<Long> getBalance(@CookieValue(value = "SESSION_STRING", required = true) String sessionString) throws UserNotFoundException {
        loadDataService.getBalance(sessionString);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(loadDataService.getBalance(sessionString));
    }
    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public ResponseEntity<List<SinglePaymentDTO>> getTransactions(@CookieValue(value = "SESSION_STRING", required = true) String sessionString) throws UserNotFoundException {
        List<SinglePaymentDTO> singlePaymentDTOS = loadDataService.getTransactions(sessionString);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(singlePaymentDTOS);
    }
}