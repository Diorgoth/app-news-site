package appnewssite.demo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResourceNotFoundException extends RuntimeException{

    private  String resourceName;//role
    private  String resourceField;//name
    private  Object object;//USER,ADMIN,1,500

    public ResourceNotFoundException(Object object) {
        this.object = object;
    }
}
