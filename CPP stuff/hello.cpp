// #include <stdio.h>
// #include <stdlib.h>
#include <map>
#include <string>
#include <iostream>

using namespace std;

class Car {
    public:
        uint8_t year;
        string model;
        string make;
        double cost;
        bool preOwned;

        Car(uint8_t year, string model, string make, double cost, bool preOwned) {
            this->year = year;
            this->model = model;
            this->make = make;
            this->cost = cost;
            this->preOwned = preOwned;
        }
    
        uint8_t getYear() {
            return this->year;
        }

        void returned() {
            this->preOwned = true;
        }

        
};

int main (int argc, char** argv) {
    map<string, int> mp;
    mp.insert({"hello", 9});
    if (!mp.count("hello")) {
        cout << "Hello key found!";
    }
    string hello = "Hello";
    
    Car car((uint8_t) 2011, "Hyundai", "Sonata", 15000, false);


    // cout << hello;

}
