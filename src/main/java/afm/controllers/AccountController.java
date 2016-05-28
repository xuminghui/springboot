package afm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import afm.entities.Account;
import afm.services.AccountService;

/**
 * Created by rchen on 3/23/16.
 */

@RestController
@RequestMapping("/accounts")
public class AccountController extends ExceptionAwarenessController {

    @Autowired
    private AccountService service;
    @RequestMapping(value="/all",method = RequestMethod.GET)
    @ResponseBody
    public Account getAll(@RequestBody Account account) {
        return account;
    }
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable Integer id) {
        return service.getAccount(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Account newAccount(@RequestBody Account account) {
        return service.addAccount(account);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Account updateAccount(@RequestBody Account newAccount) {
        return service.updateAccount(newAccount);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Account deleteAccount(Account account) {
        return service.deleteAccount(account);
    }

}
