package med.voll.api.domain.address;

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

    public void updateInfo(AddressDto data) {
        if(data.street() != null){
            this.street = data.street();
        }
        if(data.district() != null){
            this.district = data.district();
        }
        if(data.cep() != null){
            this.cep = data.cep();
        }
        if(data.city() != null){
            this.city = data.city();
        }
        if(data.uf() != null){
            this.uf = data.uf();
        }
        if(data.number() != null){
            this.number = data.number();
        }
        if(data.complement() != null){
            this.complement = data.complement();
        }
    }
}
