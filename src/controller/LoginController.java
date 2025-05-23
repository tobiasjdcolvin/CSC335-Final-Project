package controller;

import model.LoginModel;
import view.Observer;
import view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener{
    private LoginModel model;
    private LoginView myView;

    public LoginController(LoginModel model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("login")) {
            String inputUsername = myView.getLoginUsername();
            String inputPassword = myView.getLoginPassword();
            boolean successLogin = model.submitUserLogin(inputUsername, inputPassword);
        } else if (command.equals("register")) {
            String inputUsername = myView.getRegisterUsername();
            String inputPassword = myView.getRegisterPassword();
            boolean successRegister = model.submitUserRegister(inputUsername.toLowerCase(), inputPassword.toLowerCase());
        }
    }

    public void setMyView(LoginView view) {
        this.myView = view;
    }

    public void addObserver(Observer observer) {
        model.registerObserver(observer);
    }
}