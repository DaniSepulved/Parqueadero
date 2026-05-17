// package com.example.Parqueadero.model;

// @Entity
// @Table(name = "pagos")
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// @Builder
// public class Pagos {
//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "idPago")
//     private Long idPago;

//     @ManyToOne
//     @JoinColumn(name = "idReserva", nullable = false)
//     private Reservas reservas;

//     @Column(name = "monto")
//     private Double monto;

//     @Column(name = "fechaPago")
//     private java.time.LocalDate fechaPago;

//     @Column(name = "metodoPago")
//     private String metodoPago;
// }
