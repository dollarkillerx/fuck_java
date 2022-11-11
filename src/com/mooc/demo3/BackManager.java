package com.mooc.demo3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class BackManager {
    Back back;
    Scanner scanner;

    public void Run() {
        // 监听 system in 用户输入
        this.scanner = new Scanner(System.in);
        this.back = new Back();

        for (; ; ) {
            this.pv1();
            String input = this.scanner.next();
            switch (input) {
                case "1":
                    this.case1();
                    break;
                case "2":
                    this.case2();
                    break;
                case "3":
                    this.case3();
                    break;
                case "4":
                    this.case4();
                    break;
                default:
                    System.out.println("未知的输入");
            }
        }
    }

    public void pv1() {
        System.out.println("おおやく銀行");
        System.out.println("=======================================");
        System.out.println("1. 获取所以银行账户信息");
        System.out.println("2. 查询余额度");
        System.out.println("3. 存款");
        System.out.println("4. 转账");
    }

    public void case1() {
        ArrayList<String> backList = this.back.GetAllBackUser();
        System.out.println("-----------------------");
        backList.forEach(System.out::println);
        System.out.println("-----------end------------");
    }

    public void case2() {
        System.out.println("-----------------------");
        System.out.println("请输入账户: ");
        String account = scanner.next();
        System.out.println("请输入密码: ");
        String password = scanner.next();

        try {
            double v = this.back.SearchAmount(account, password);
            System.out.printf("您的余额: %.2f \n", v);
        } catch (Exception e) {
            System.out.printf("错误: %s \n", e.getMessage());
        }

        System.out.println("-----------end------------");
    }

    public void case3() {
        System.out.println("-----------------------");
        System.out.println();
        System.out.println("请输入账户: ");
        String account = scanner.next();
        System.out.println("请输入密码: ");
        String password = scanner.next();
        System.out.println("请输入金额: ");
        String amountStr = scanner.next();
        try {
            double amount = Double.parseDouble(amountStr);
            this.back.SaveMoney(account, password, amount);
        } catch (Exception e) {
            System.out.printf("错误: %s \n", e.getMessage());
        }
        System.out.println("-----------end------------");
    }

    public void case4() {
        System.out.println("-----------------------");
        System.out.println();
        System.out.println("请输入账户: ");
        String account = scanner.next();
        System.out.println("请输入密码: ");
        String password = scanner.next();
        System.out.println("请输入对方账户: ");
        String toAccount = scanner.next();
        System.out.println("请输入金额: ");
        String amountStr = scanner.next();
        try {
            double amount = Double.parseDouble(amountStr);
            this.back.Transfer(account, password, toAccount, amount);
        } catch (Exception e) {
            System.out.printf("错误: %s \n", e.getMessage());
        }
        System.out.println("-----------end------------");
    }

}

class Back {
    private HashMap<String, BackInfo> back; // 具體存儲數據

    public Back() {
        back = new HashMap<>();
    }

    // SearchAmount 查詢
    public double SearchAmount(String account, String password) throws Exception {
        try {
            BackInfo backInfo = back.get(account);
            if (!Objects.equals(backInfo.password, password)) {
                throw new Exception("密碼不正確");
            }

            return backInfo.amount;
        } catch (Exception e) {
            throw new Exception("用戶不存在");
        }
    }

    // SaveMoney 存錢
    public void SaveMoney(String account, String password, Double amount) {
        try {
            BackInfo backInfo = back.get(account);
            backInfo.amount += amount;
        } catch (Exception e) {
            BackInfo bk = new BackInfo(account);
            bk.setAmount(amount);
            bk.setPassword(password);
            back.put(account, bk);
        }
    }

    // Transfer 轉賬
    public void Transfer(String fromAccount, String password, String toAccount, Double amount) throws Exception {
        if (!back.containsKey(fromAccount)) {
            back.put(fromAccount, new BackInfo(fromAccount));
            throw new Exception("fromAccount 用戶不存在");
        }
        if (!back.containsKey(toAccount)) {
            back.put(toAccount, new BackInfo(toAccount));
            throw new Exception("toAccount 用戶不存在");
        }
        BackInfo fromAccountBackInfo = back.get(fromAccount);
        BackInfo toAccountBackInfo = back.get(toAccount);

        if (!Objects.equals(fromAccountBackInfo.password, password)) {
            throw new Exception("密码错误");
        }

        if (fromAccountBackInfo.amount < amount) {
            throw new Exception("用戶餘額不足");
        }

        fromAccountBackInfo.amount -= amount;
        toAccountBackInfo.amount += amount;

        back.put(fromAccount, fromAccountBackInfo);
        back.put(toAccount, toAccountBackInfo);
    }

    // GetAllBackUser 获取所有银行账户信息
    public ArrayList<String> GetAllBackUser() {
        ArrayList<String> response = new ArrayList<>();
        this.back.forEach((key, val) -> {
            response.add(String.format("Account %s Amount: %.2f", val.account, val.amount));
        });

        return response;
    }
}

class BackInfo {
    String account;
    String password;
    Double amount;

    public BackInfo(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
