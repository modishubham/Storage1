package com.example.shubham.storageandroid;

public class Person {

    int _id;
    String _name;
    int _age;
    String _address;

    public Person(int _id, String _name, int _age, String _address) {
        this._id = _id;
        this._name = _name;
        this._age = _age;
        this._address = _address;
    }

    public Person(String _name, int _age, String _address) {
        this._name = _name;
        this._age = _age;
        this._address = _address;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_age() {
        return _age;
    }

    public void set_age(int _age) {
        this._age = _age;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }
}
