package app.domain.dto;


import app.domain.model.Person;
import com.google.gson.annotations.Expose;

import java.util.Set;
import java.util.stream.Collectors;

public class PersonDto {
    @Expose
    private String firstName;
    @Expose
    private Set<PhoneNumberDto> phoneNumbers;
    @Expose
    private AddressDto addressDto;

    public PersonDto(Person p) {
       this.firstName = p.getFirstName();
        this.phoneNumbers = p
                .getPhoneNumbers()
                .stream()
                .map(phone -> new PhoneNumberDto(phone.getNumber()))
                .collect(Collectors.toSet());
        this.addressDto = new AddressDto(p.getAddress());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Set<PhoneNumberDto> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<PhoneNumberDto> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
