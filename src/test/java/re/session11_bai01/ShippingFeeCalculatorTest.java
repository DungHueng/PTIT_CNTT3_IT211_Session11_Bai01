package re.session11_bai01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import re.session11_bai01.Controller.ShippingFeeCalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ShippingFeeCalculatorTest {
    private ShippingFeeCalculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new ShippingFeeCalculator();
    }

    @Test
    void calculateFee_shouldReturnBaseFee_whenWeightLessThanOrEqualOneKgAndDistanceLessThanTenKm() {
        double fee = calculator.calculateFee(1.0, 5.0);
        assert fee == 50000;
    }
    @Test
    void calculateFee_shouldCalculateCorrectly_whenWeightIsIntegerAndDistanceFromTenToUnderFiftyKm() {
        double fee = calculator.calculateFee(3.0, 20.0);
         /**
          * 1kg đầu = 50.000
          * 2kg tiếp theo = 20.000
          * 20km * 5.000 = 100.000
          * Tổng = 170.000
          * */
        assertThat(fee).isEqualTo(170000);
    }
    @Test
    void calculateFee_shouldRoundUpExtraWeight_whenWeightIsDecimalAndDistanceGreaterThanFiftyKm() {
        double fee = calculator.calculateFee(1.5, 60.0);
        /**
         * 1kg đầu = 50.000
         * 0.5kg tiếp theo làm tròn lên = 10.000
         * 60km * 4.000 = 240.000
         * Tổng = 300.000\
         **/
        assertThat(fee).isEqualTo(300000);
    }

    @Test
    void calculateFee_shouldRoundUpExtraWeight_whenWeightIsTwoPointThreeKg() {
        double fee = calculator.calculateFee(2.3, 60.0);
        /** 1kg đầu = 50.000
        * 1.3kg tiếp theo làm tròn lên = 2kg * 10.000 = 20.000
        * 60km * 4.000 = 240.000
        * Tổng = 310.000
         **/
        assertThat(fee).isEqualTo(310000);
    }

    @Test
    void calculateFee_shouldCalculateCorrectly_whenDistanceExactlyTenKm() {
        double fee = calculator.calculateFee(1.0, 10.0);
        // 50.000 + 10 * 5.000
        assertThat(fee).isEqualTo(100000);
    }

    @Test
    void calculateFee_shouldCalculateCorrectly_whenDistanceExactlyFiftyKm() {
        double fee = calculator.calculateFee(1.0, 50.0);
        // 50.000 + 50 * 4.000
        assertThat(fee).isEqualTo(250000);
    }

    @Test
    void calculateFee_shouldThrowException_whenWeightIsInvalid() {
        assertThatThrownBy(() -> calculator.calculateFee(0, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Trọng lượng và khoảng cách phải là số dương");
    }

    @Test
    void calculateFee_shouldThrowException_whenDistanceIsInvalid() {
        assertThatThrownBy(() -> calculator.calculateFee(1, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Trọng lượng và khoảng cách phải là số dương");
    }
}