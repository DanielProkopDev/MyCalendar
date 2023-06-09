package Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name= "day")
public class Day extends AbstractEntity{
    @Column(name= "yeardate")
    private Integer yearDate;
    @Column(name= "monthdate")
    private  Integer monthDate;
    @Column(name= "daydate")
    private Integer dayDate;
    @Column(name="dayText")
    private String dayText;


    @ManyToMany
    @JoinTable(
            name = "user_day",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "day_id")
    )
    private List<User> users;

    @ManyToMany
    @JoinTable(
            name = "day_meals",
            joinColumns = @JoinColumn(name = "day_id"),
            inverseJoinColumns = @JoinColumn(name = "meals_id")
    )
    private List<Meals> meals ;


    public Day (Integer year, Integer month, Integer day){
        this.yearDate = year;
        this.monthDate = month;
        this.dayDate = day;
    }

    public Day() {

    }

    public String getDietText() {
        return dayText;
    }

    public void setDietText(String dietText) {
        this.dayText = dietText;
    }

    public Integer getYearDate() {
        return yearDate;
    }

    public void setYearDate(Integer yearDate) {
        this.yearDate = yearDate;
    }

    public Integer getMonthDate() {
        return monthDate;
    }

    public void setMonthDate(Integer monthDate) {
        this.monthDate = monthDate;
    }

    public Integer getDayDate() {
        return dayDate;
    }

    public void setDayDate(Integer dayDate) {
        this.dayDate = dayDate;
    }

    public String getDayText() {
        return dayText;
    }

    public void setDayText(String dayText) {
        this.dayText = dayText;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Meals> getMeals() {
        return meals;
    }

    public void setMeals(List<Meals> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "Day{" +
                "yearDate=" + yearDate +
                ", monthDate=" + monthDate +
                ", dayDate=" + dayDate +
                ", dayText='" + dayText + '\''  +
                '}';
    }


}
