package jpapractice.practice.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BookState {

    @Id
    @GeneratedValue
    private Long id;

    private boolean isMarked;
    private boolean isOutlined;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    public boolean isOutlined() {
        return isOutlined;
    }

    public void setOutlined(boolean outlined) {
        isOutlined = outlined;
    }
}
