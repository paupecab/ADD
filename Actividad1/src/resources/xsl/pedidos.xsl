<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!-- salida html -->
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <!-- plantilla raiz que se aplica sobre todo el xml -->
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
                <title>Pedidos</title>
                <style>
                    body {
                    margin: 0;
                    padding: 40px 20px;
                    font-family: "Trebuchet MS", "Comic Sans MS", sans-serif;
                    color: #e9d5ff;
                    background-color: #0b1026;
                    background-image:
                    radial-gradient(2px 2px at 20px 30px, #ffffff, transparent),
                    radial-gradient(1.5px 1.5px at 90px 80px, #e9d5ff, transparent),
                    radial-gradient(2px 2px at 150px 140px, #ffffff, transparent),
                    radial-gradient(1px 1px at 60px 160px, #c4b5fd, transparent),
                    radial-gradient(1.5px 1.5px at 180px 20px, #f5d0fe, transparent),
                    radial-gradient(1px 1px at 120px 110px, #ffffff, transparent);
                    background-size: 200px 200px;
                    }

                    h1 {
                    text-align: center;
                    color: #d8b4fe;
                    text-shadow: 0 0 12px #a855f7;
                    margin-bottom: 30px;
                    }

                    table {
                    margin: 0 auto;
                    border-collapse: separate;
                    border-spacing: 0;
                    border: 2px solid #a78bfa;
                    border-radius: 14px;
                    overflow: hidden;
                    box-shadow: 0 0 25px rgba(168, 85, 247, 0.5);
                    }

                    th {
                    background-color: #7c3aed;
                    color: #f5f3ff;
                    padding: 12px 24px;
                    letter-spacing: 1px;
                    }

                    td {
                    padding: 10px 24px;
                    text-align: center;
                    border-top: 1px solid #4c1d95;
                    }

                    tr:nth-child(odd) td  { background-color: rgba(139, 92, 246, 0.18); }
                    tr:nth-child(even) td { background-color: rgba(192, 132, 252, 0.10); }
                    tr:hover td { background-color: rgba(216, 180, 254, 0.35); }
                </style>
            </head>
            <body>
                <h1>✨ Pedidos de PedidosYa! ✨</h1>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Cliente</th>
                        <th>Producto</th>
                        <th>Cantidad</th>
                    </tr>
                    <xsl:for-each select="pedidos/pedido">
                        <tr>
                            <td><xsl:value-of select="@id"/></td>
                            <td><xsl:value-of select="@clienteId"/></td>
                            <td><xsl:value-of select="producto"/></td>
                            <td><xsl:value-of select="cantidad"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>