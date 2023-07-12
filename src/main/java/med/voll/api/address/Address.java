package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String district;
    private String cep;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public Address(AddressDto addressDto) {
        this.street = addressDto.street();
        this.district = addressDto.district();
        this.cep = addressDto.cep();
        this.city = addressDto.city();
        this.uf = addressDto.uf();
        this.number = addressDto.number();
        this.complement = addressDto.complement();
    }
}
