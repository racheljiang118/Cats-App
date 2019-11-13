package com.example.cats;

        public class Cats{

        private String id;
        private String name;
        private Weight weight;
        private String description;
        private String temperament;
        private String origin;
        private String life_span;
        private String wikipedia_url;
        private int dog_friendly;

        public Cats (String name) {
        this.name = name;
        }

        public Cats(String id, String name, Weight weight,  String description, String temperament,
        String origin, String life_span, String wikipedia_url, int dog_friendly) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.temperament = temperament;
        this.origin = origin;
        this.life_span = life_span;
        this.wikipedia_url = wikipedia_url;
        this.dog_friendly = dog_friendly;
        }

        public class Weight {
                private String imperial;
                private String metric;

                public String getImperial() {
                return imperial;
                }

                public void setImperial(String imperial) {
                this.imperial = imperial;
                }

                public String getMetric() {
                return metric;
                }

                public void setMetric(String metric) {
                this.metric = metric;
                }
        }

        public Weight getWeight() {
        return weight;
        }

        public void setWeight(Weight weight) {
        this.weight = weight;
        }

        public String getDescription() {
        return description;
        }

        public void setDescription(String description) {
        this.description = description;
        }

        public String getId() {
        return id;
        }

        public void setId(String id) {
        this.id = id;
        }

        public String getName() {
        return name;
        }

        public void setName(String name) {
        this.name = name;
        }

        public String getTemperament() {
        return temperament;
        }

        public void setTemperament(String temperament) {
        this.temperament = temperament;
        }

        public String getOrigin() {
        return origin;
        }

        public void setOrigin(String origin) {
        this.origin = origin;
        }

        public String getLife_span() {
        return life_span;
        }

        public void setLife_span(String life_span) {
        this.life_span = life_span;
        }

        public String getWikipedia_url() {
        return wikipedia_url;
        }

        public void setWikipedia_url(String wikipedia_url) {
        this.wikipedia_url = wikipedia_url;
        }

        public int getDog_friendly() {
        return dog_friendly;
        }

        public void setDog_friendly(int dog_friendly) {
        this.dog_friendly = dog_friendly;
        }
        }

