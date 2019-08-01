package dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class YandexSpellerDto {
//based on JSON response
    private Integer code;
    private Integer pos;
    private Integer row;
    private Integer col;
    private Integer len;
    private String word;
    private List<String> s;

}
