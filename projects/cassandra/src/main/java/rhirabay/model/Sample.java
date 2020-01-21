package rhirabay.model;

import lombok.Value;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Value
@Table("t_sample")
public class Sample {
    @PrimaryKey
    private String key;
    private String value;
}
