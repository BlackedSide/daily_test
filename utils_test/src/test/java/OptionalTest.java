import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Optional;

public class OptionalTest {
    private static final String NULL_VALUE = "-";

    @Test
    public void replaceNull() {
        Price test = null;
        Price replace = new Price(NULL_VALUE);
        Optional<Price> replacement = Optional.of(replace);
        Optional<Price> res = Optional.fromNullable(test).or(replacement);
        Price res2 = Optional.fromNullable(test).or(replace);
        System.out.println(res.get().getPrice());
        System.out.println(res2.getPrice());
        Optional<Price> absent = Optional.absent();
        Assert.assertNull(absent.get()); // IllegalStateException
        System.out.println(absent.or(replace).getPrice());
    }

    @Test
    public void tryNull() {
        Price test1 = new Price("100");
        Price test2 = null;
        Optional<Price> tryNull = null;
        Price replacement = new Price(NULL_VALUE);
        Price res;
        try {
//            tryNull = Optional.of(test1);
            tryNull = Optional.of(test2);
        } catch (NullPointerException ex) {
            tryNull = Optional.of(replacement);
        } finally {
            res = tryNull.get();
        }
        System.out.println(res.getPrice());
    }

    private static class Price {
        private String price;

        public Price() {}

        public Price(String price) {
            this.price = price;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }
}
