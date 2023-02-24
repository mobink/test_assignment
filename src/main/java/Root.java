
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class Root {

        @NonNull
        @JsonProperty("plateNo")
        private String plateNo;
        @NonNull
        @JsonProperty("driverName")
        private String driverName;
        @NonNull
        @JsonProperty("lat")
        private Double lat;
        @NonNull
        @JsonProperty("lng")
        private Double lng;
        @NonNull
        @JsonProperty("location")
        private String location;
        @JsonProperty("imageURL")
        private String imageURL;
        @NonNull
        @JsonProperty("lastUpdated")
        private String lastUpdated;
        public String getPlateNo() {
                return plateNo;
        }

        public void setPlateNo(String plateNo) {
                this.plateNo = plateNo;
        }

        public String getDriverName() {
                return driverName;
        }

        public void setDriverName(String driverName) {
                this.driverName = driverName;
        }

        public Double getLat() {
                return lat;
        }

        public void setLat(Double lat) {
                this.lat = lat;
        }

        public Double getLng() {
                return lng;
        }

        public void setLng(Double lng) {
                this.lng = lng;
        }

        public String getLocation() {
                return location;
        }

        public void setLocation(String location) {
                this.location = location;
        }

        public String getImageURL() {
                return imageURL;
        }

        public void setImageURL(String imageURL) {
                this.imageURL = imageURL;
        }

        public String getLastUpdated() {
                return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
                this.lastUpdated = lastUpdated;
        }



}