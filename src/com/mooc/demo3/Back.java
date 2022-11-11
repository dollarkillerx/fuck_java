package com.mooc.demo3;

import java.util.HashMap;
import java.util.Objects;

public class Back {
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
    public void SaveMoney(String account, Double amount) {
        try {
            BackInfo backInfo = back.get(account);
            backInfo.amount += amount;
        } catch (Exception e) {
            BackInfo bk =  new BackInfo(account);
            bk.setAmount(amount);
            back.put(account, bk);
        }
    }

    // Transfer 轉賬
    public void Transfer(String fromAccount, String toAccount, Double amount) throws Exception {
        if (!back.equals(fromAccount)) {
            back.put(fromAccount, new BackInfo(fromAccount));
            throw new Exception("用戶不存在");
        }
        if (!back.equals(toAccount)) {
            back.put(toAccount, new BackInfo(toAccount));
            throw new Exception("用戶不存在");
        }

        BackInfo fromAccountBackInfo = back.get(fromAccount);
        BackInfo toAccountBackInfo = back.get(toAccount);

        if (fromAccountBackInfo.amount < amount) {
            throw new Exception("用戶餘額不足");
        }

        fromAccountBackInfo.amount -= amount;
        toAccountBackInfo.amount += amount;

        back.put(fromAccount, fromAccountBackInfo);
        back.put(toAccount, toAccountBackInfo);
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
