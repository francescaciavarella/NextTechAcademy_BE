package com.infobasic.ciavarella.nexttechacademy.service;

import com.infobasic.ciavarella.nexttechacademy.exception.ResourceNotFoundException;
import com.infobasic.ciavarella.nexttechacademy.model.Payment;
import com.infobasic.ciavarella.nexttechacademy.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Page<Payment> getAllPayments(Pageable pageable) {
        return paymentRepository.findAll(pageable);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment con ID " + id + " non trovato"));
    }

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public void deletePaymentById(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
        } else {
            throw new RuntimeException("Payment not found with id: " + id);
        }
    }

    public Payment updatePayment(Long id, Payment payment) {
        Optional<Payment> existingPaymentOptional = paymentRepository.findById(id);
        if (!existingPaymentOptional.isPresent()) {
            throw new RuntimeException("Payment not found with id: " + id);
        }
        Payment existingPayment = existingPaymentOptional.get();
        existingPayment.setStudent(payment.getStudent());
        existingPayment.setAmount(payment.getAmount());
        existingPayment.setPaymentDate(payment.getPaymentDate());
        existingPayment.setPaymentMethod(payment.getPaymentMethod());
        existingPayment.setDescription(payment.getDescription());
        return paymentRepository.save(existingPayment);
    }

    public Page<Payment> getPaymentsByStudentId(Long studentId, Pageable pageable) {
        return paymentRepository.findByStudentId(studentId, pageable);
    }

    public List<Payment> getPaymentsByDateRange(LocalDate startDate, LocalDate endDate) {
        return paymentRepository.findByPaymentDateBetween(startDate, endDate);
    }

    //TODO: getPaymentsByAmountRange;
}
