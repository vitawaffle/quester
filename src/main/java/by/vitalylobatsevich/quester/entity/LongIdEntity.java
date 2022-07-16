package by.vitalylobatsevich.quester.entity;

import javax.persistence.*;
import lombok.*;

@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class LongIdEntity extends AppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
