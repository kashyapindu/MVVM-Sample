package com.mvvmpattern.model.response;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by indu_kashyap on 11/8/2016.
 */

public class GetFavouriteResponse {

    /**
     * status : {"code":"200","message":"success","Response":[{"user_id":267,"id":9,"flag":1,"user_pic":"http://softprodigyphp.in/TravelerApp2/img/Users/2740Image.png","source_location":"Mohali","destination_location":"Zurich","source_lat":"30.7046486","source_long":"76.71787259999999","destination_long":"8.635645199999999","destination_lat":"47.359536","source_image":"https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAABQ11P9w3z1eZuj0APtlDNstE3gBFFnWPI-NsJKJ-V0BavHbkiqdPQddr_1MAp8-QPoRz20fClVqe_2y39N2nB1pA2qTZwx7KmSSXCRGHYh1QjDglqmSXOnziB92eqwYqs3NM3DhNjvVJBz8CE_p1oMVFkeUX65tNeq5GKpqg4QuEhCSMlLU0oFWisQM2uhVwfCSGhTzLlFNSyaZ2MR693fVKJiFioDFqg&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320","destination_image":"https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAALtnkN0d7Yo-584KDxVq6aP-R9n09WBnep9ywWpdMU9tpePQnJbRI2tNaAXHxlY8qkReJhZP0hQ4bqDHdDsYQG3RrjIrY69za73z1_9oG2hpr9TkoI-yNLMV-pOvcGCxQBcrC8jf_D2TbP47tcRQu9Bx2jp5wFpQg2pg5JbvNeVrEhBneScihnQa9AvJ_78HpHsWGhTfVekyvynL3PPSmVd4rn0o3S83eg&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320","summary":"argdrtg","category":"Food","fromDate":"2016-12-05T00:00:00+0000","toDate":"2016-12-08T00:00:00+0000","approx_weight":"565","package_image":"s:16:\"550938_test0.png\";"},{"user_id":267,"id":10,"flag":1,"user_pic":"http://softprodigyphp.in/TravelerApp2/img/Users/2740Image.png","source_location":"Mohali","destination_location":"West Bengal","source_lat":"30.7046486","source_long":"76.71787259999999","destination_long":"87.85497549999999","destination_lat":"22.9867569","source_image":"https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAAN9JFkMSsYu-_QFT_ZBiZSZhALEnN7Hzod1tNBPKSmxwv9KJuZ43tm8QtruUDBXYsxgPRcPdLwJwYPKdryyViqu-oYj0MCtT93HB2a5CtZlcHt5WikDfr0_KJCGdHAVj-eDWfVHWkwTr6_0P5zOcM5ocHOmabnnAljcoOKvyHLF-EhCnbOx2K9Yd-yyU1NteglMaGhRvhIVq5MpMm4_2j5xLn8uS5OfDtg&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320","destination_image":"https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAAOtPkGwgAuz6qcbcQUAPiD2mf1KTrE93gt29HDwEF5dW33yidVWhM5PnrP5wO9xFfXj3FW1v9iCGsVcUgyVU8y28eUVNAI0_cgkLOdCFLQEO8iQMrpvRQYooUfJ6S8KAWigqMR5z7OlWphmHXs7mCG9usvL7lpAqdVDwgP5cO8KjEhB76HJOCfkk9CQ-zZyso1e4GhS_uEj8aLcO-7bGkNdS_0LMD-KPKw&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320","summary":"tfhfthty","category":"Documents","fromDate":"2016-12-06T00:00:00+0000","toDate":"2016-12-12T00:00:00+0000","approx_weight":"58","package_image":"s:50:\"436694_test0.png,222911_test1.png,129243_test2.png\";"}]}
     */

    private StatusBean status;

    public static GetFavouriteResponse objectFromData(String str) {

        return new Gson().fromJson(str, GetFavouriteResponse.class);
    }

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    public static class StatusBean {
        /**
         * code : 200
         * message : success
         * Response : [{"user_id":267,"id":9,"flag":1,"user_pic":"http://softprodigyphp.in/TravelerApp2/img/Users/2740Image.png","source_location":"Mohali","destination_location":"Zurich","source_lat":"30.7046486","source_long":"76.71787259999999","destination_long":"8.635645199999999","destination_lat":"47.359536","source_image":"https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAABQ11P9w3z1eZuj0APtlDNstE3gBFFnWPI-NsJKJ-V0BavHbkiqdPQddr_1MAp8-QPoRz20fClVqe_2y39N2nB1pA2qTZwx7KmSSXCRGHYh1QjDglqmSXOnziB92eqwYqs3NM3DhNjvVJBz8CE_p1oMVFkeUX65tNeq5GKpqg4QuEhCSMlLU0oFWisQM2uhVwfCSGhTzLlFNSyaZ2MR693fVKJiFioDFqg&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320","destination_image":"https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAALtnkN0d7Yo-584KDxVq6aP-R9n09WBnep9ywWpdMU9tpePQnJbRI2tNaAXHxlY8qkReJhZP0hQ4bqDHdDsYQG3RrjIrY69za73z1_9oG2hpr9TkoI-yNLMV-pOvcGCxQBcrC8jf_D2TbP47tcRQu9Bx2jp5wFpQg2pg5JbvNeVrEhBneScihnQa9AvJ_78HpHsWGhTfVekyvynL3PPSmVd4rn0o3S83eg&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320","summary":"argdrtg","category":"Food","fromDate":"2016-12-05T00:00:00+0000","toDate":"2016-12-08T00:00:00+0000","approx_weight":"565","package_image":"s:16:\"550938_test0.png\";"},{"user_id":267,"id":10,"flag":1,"user_pic":"http://softprodigyphp.in/TravelerApp2/img/Users/2740Image.png","source_location":"Mohali","destination_location":"West Bengal","source_lat":"30.7046486","source_long":"76.71787259999999","destination_long":"87.85497549999999","destination_lat":"22.9867569","source_image":"https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAAN9JFkMSsYu-_QFT_ZBiZSZhALEnN7Hzod1tNBPKSmxwv9KJuZ43tm8QtruUDBXYsxgPRcPdLwJwYPKdryyViqu-oYj0MCtT93HB2a5CtZlcHt5WikDfr0_KJCGdHAVj-eDWfVHWkwTr6_0P5zOcM5ocHOmabnnAljcoOKvyHLF-EhCnbOx2K9Yd-yyU1NteglMaGhRvhIVq5MpMm4_2j5xLn8uS5OfDtg&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320","destination_image":"https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAAOtPkGwgAuz6qcbcQUAPiD2mf1KTrE93gt29HDwEF5dW33yidVWhM5PnrP5wO9xFfXj3FW1v9iCGsVcUgyVU8y28eUVNAI0_cgkLOdCFLQEO8iQMrpvRQYooUfJ6S8KAWigqMR5z7OlWphmHXs7mCG9usvL7lpAqdVDwgP5cO8KjEhB76HJOCfkk9CQ-zZyso1e4GhS_uEj8aLcO-7bGkNdS_0LMD-KPKw&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320","summary":"tfhfthty","category":"Documents","fromDate":"2016-12-06T00:00:00+0000","toDate":"2016-12-12T00:00:00+0000","approx_weight":"58","package_image":"s:50:\"436694_test0.png,222911_test1.png,129243_test2.png\";"}]
         */

        private String code;
        private String message;
        private List<ResponseBean> Response;

        public static StatusBean objectFromData(String str) {

            return new Gson().fromJson(str, StatusBean.class);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public List<ResponseBean> getResponse() {
            return Response;
        }

        public void setResponse(List<ResponseBean> Response) {
            this.Response = Response;
        }

        public static class ResponseBean {
            /**
             * user_id : 267
             * id : 9
             * flag : 1
             * user_pic : http://softprodigyphp.in/TravelerApp2/img/Users/2740Image.png
             * source_location : Mohali
             * destination_location : Zurich
             * source_lat : 30.7046486
             * source_long : 76.71787259999999
             * destination_long : 8.635645199999999
             * destination_lat : 47.359536
             * source_image : https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAABQ11P9w3z1eZuj0APtlDNstE3gBFFnWPI-NsJKJ-V0BavHbkiqdPQddr_1MAp8-QPoRz20fClVqe_2y39N2nB1pA2qTZwx7KmSSXCRGHYh1QjDglqmSXOnziB92eqwYqs3NM3DhNjvVJBz8CE_p1oMVFkeUX65tNeq5GKpqg4QuEhCSMlLU0oFWisQM2uhVwfCSGhTzLlFNSyaZ2MR693fVKJiFioDFqg&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320
             * destination_image : https://maps.googleapis.com/maps/api/place/photo?photoreference=CoQBdwAAALtnkN0d7Yo-584KDxVq6aP-R9n09WBnep9ywWpdMU9tpePQnJbRI2tNaAXHxlY8qkReJhZP0hQ4bqDHdDsYQG3RrjIrY69za73z1_9oG2hpr9TkoI-yNLMV-pOvcGCxQBcrC8jf_D2TbP47tcRQu9Bx2jp5wFpQg2pg5JbvNeVrEhBneScihnQa9AvJ_78HpHsWGhTfVekyvynL3PPSmVd4rn0o3S83eg&key=AIzaSyAo7X0-kEIl3B56rJ0-JtU2NqFFAez2_Hg=&sensor=false&maxwidth=320
             * summary : argdrtg
             * category : Food
             * fromDate : 2016-12-05T00:00:00+0000
             * toDate : 2016-12-08T00:00:00+0000
             * approx_weight : 565
             * package_image : s:16:"550938_test0.png";
             */

            private int user_id;
            private int id;
            private int flag;
            private String user_pic;
            private String source_location;
            private String destination_location;
            private String source_lat;
            private String source_long;
            private String destination_long;
            private String destination_lat;
            private String source_image;
            private String destination_image;
            private String summary;
            private String category;
            private String fromDate;
            private String toDate;
            private String approx_weight;
            private String package_image;

            public static ResponseBean objectFromData(String str) {

                return new Gson().fromJson(str, ResponseBean.class);
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getFlag() {
                return flag;
            }

            public void setFlag(int flag) {
                this.flag = flag;
            }

            public String getUser_pic() {
                return user_pic;
            }

            public void setUser_pic(String user_pic) {
                this.user_pic = user_pic;
            }

            public String getSource_location() {
                return source_location;
            }

            public void setSource_location(String source_location) {
                this.source_location = source_location;
            }

            public String getDestination_location() {
                return destination_location;
            }

            public void setDestination_location(String destination_location) {
                this.destination_location = destination_location;
            }

            public String getSource_lat() {
                return source_lat;
            }

            public void setSource_lat(String source_lat) {
                this.source_lat = source_lat;
            }

            public String getSource_long() {
                return source_long;
            }

            public void setSource_long(String source_long) {
                this.source_long = source_long;
            }

            public String getDestination_long() {
                return destination_long;
            }

            public void setDestination_long(String destination_long) {
                this.destination_long = destination_long;
            }

            public String getDestination_lat() {
                return destination_lat;
            }

            public void setDestination_lat(String destination_lat) {
                this.destination_lat = destination_lat;
            }

            public String getSource_image() {
                return source_image;
            }

            public void setSource_image(String source_image) {
                this.source_image = source_image;
            }

            public String getDestination_image() {
                return destination_image;
            }

            public void setDestination_image(String destination_image) {
                this.destination_image = destination_image;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getFromDate() {
                return fromDate;
            }

            public void setFromDate(String fromDate) {
                this.fromDate = fromDate;
            }

            public String getToDate() {
                return toDate;
            }

            public void setToDate(String toDate) {
                this.toDate = toDate;
            }

            public String getApprox_weight() {
                return approx_weight;
            }

            public void setApprox_weight(String approx_weight) {
                this.approx_weight = approx_weight;
            }

            public String getPackage_image() {
                return package_image;
            }

            public void setPackage_image(String package_image) {
                this.package_image = package_image;
            }
        }
    }
}
