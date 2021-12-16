package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    private int orderId;
    private String firstName;
    private String lastName;
    private String addressLine;
    private String addressState;
    private String addressCity;
    private String timeElapsed;
    private int addressZipCode;
    private String email;
    private String phoneNumber;
    private List<MenuItem> menuItems;
    private List<CustomPizza> customPizza;
    private boolean isDelivery;
    private boolean isCompleted;
    private long creditCardNumber;
    private int creditCardExpMonth;
    private int creditCardExpYear;
    private int creditCardCcv;
    private BigDecimal orderTotal;

    public Order() {

    }

    public String getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(String timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getAddressState() {
        return addressState;
    }

    public void setAddressState(String addressState) {
        this.addressState = addressState;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public int getAddressZipCode() {
        return addressZipCode;
    }

    public void setAddressZipCode(int addressZipCode) {
        this.addressZipCode = addressZipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public List<CustomPizza> getCustomPizza() {
        return customPizza;
    }

    public void setCustomPizza(List<CustomPizza> customPizza) {
        this.customPizza = customPizza;
    }

    public boolean getIsDelivery() {
        return isDelivery;
    }

    public void setIsDelivery(boolean delivery) {
        isDelivery = delivery;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean completed) {
        isCompleted = completed;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCreditCardExpMonth() {
        return creditCardExpMonth;
    }

    public void setCreditCardExpMonth(int creditCardExpMonth) {
        this.creditCardExpMonth = creditCardExpMonth;
    }

    public int getCreditCardExpYear() {
        return creditCardExpYear;
    }

    public void setCreditCardExpYear(int creditCardExpYear) {
        this.creditCardExpYear = creditCardExpYear;
    }

    public int getCreditCardCcv() {
        return creditCardCcv;
    }

    public void setCreditCardCcv(int creditCardCcv) {
        this.creditCardCcv = creditCardCcv;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }
}
