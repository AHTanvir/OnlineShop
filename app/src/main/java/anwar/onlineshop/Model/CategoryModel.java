package anwar.onlineshop.Model;

/**
 * Created by anwar on 9/10/2017.
 */

public class CategoryModel {
    private String imageUrl;
    private String name;

    public CategoryModel(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
