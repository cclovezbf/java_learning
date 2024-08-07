package responsibility.handle;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String name;
    private RequestType requestType;

}
