<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        div.blueTable {
            border: 1px solid #1C6EA4;
            background-color: #EEEEEE;
            width: 100%;
            text-align: left;
            border-collapse: collapse;
        }

        .divTable.blueTable .divTableCell,
        .divTable.blueTable .divTableHead {
            border: 1px solid #AAAAAA;
            padding: 3px 2px;
        }

        .divTable.blueTable .divTableBody .divTableCell {
            font-size: 13px;
        }

        .divTable.blueTable .divTableRow:nth-child(even) {
            background: #D0E4F5;
        }

        .divTable.blueTable .divTableHeading {
            background: #1C6EA4;
            background: -moz-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);
            background: -webkit-linear-gradient(top, #5592bb 0%, #327cad 66%, #1C6EA4 100%);
            background: linear-gradient(to bottom, #5592bb 0%, #327cad 66%, #1C6EA4 100%);
            border-bottom: 2px solid #444444;
        }

        .divTable.blueTable .divTableHeading .divTableHead {
            font-size: 15px;
            font-weight: bold;
            color: #FFFFFF;
            border-left: 2px solid #D0E4F5;
        }

        .nom {
            font-size: 15px;
            font-weight: bold;
            border: none;
            text-align: center;
        }

        .divTable.blueTable .divTableHeading .divTableHead:first-child {
            border-left: none;
        }

        .blueTable .tableFootStyle {
            font-size: 14px;
            font-weight: bold;
            color: #FFFFFF;
            background: #D0E4F5;
            background: -moz-linear-gradient(top, #dcebf7 0%, #d4e6f6 66%, #D0E4F5 100%);
            background: -webkit-linear-gradient(top, #dcebf7 0%, #d4e6f6 66%, #D0E4F5 100%);
            background: linear-gradient(to bottom, #dcebf7 0%, #d4e6f6 66%, #D0E4F5 100%);
            border-top: 2px solid #444444;
        }

        .blueTable .tableFootStyle {
            font-size: 14px;
        }

        .blueTable .tableFootStyle .links {
            text-align: right;
        }

        .blueTable .tableFootStyle .links a {
            display: inline-block;
            background-color: #1C6EA4;
            color: #FFFFFF;
            padding: 2px 8px;
            border-radius: 5px;
        }

        .blueTable.outerTableFooter {
            border-top: none;
        }

        .blueTable.outerTableFooter .tableFootStyle {
            padding: 3px 5px;
        }

        /* DivTable.com */
        .divTable {
            display: table;
        }

        .divTableRow {
            display: table-row;
        }

        .divTableHeading {
            display: table-header-group;
        }

        .divTableCell,
        .divTableHead {
            display: table-cell;
        }

        .divTableHeading {
            display: table-header-group;
        }

        .divTableFoot {
            display: table-footer-group;
        }

        .divTableBody {
            display: table-row-group;
        }

        .niveau {
            width: 50%;
        }

        .container-legende {
            width: 400px;
            display: flex;
            justify-content: space-around;
            align-items: center;
             margin-top: 10px;
        }

        .container-legende:first-child {
            min-width: 30%;
        }
        .span-value{
        display:inline-block;
        width: 50px;
         text-align: center;
         
        }
        
    </style>
    <title>GrillePositionnement</title>
</head>

<body>
    <div class="divTable blueTable">
        <div class="divTableHeading">
            <div class="divTableRow" style="background: #0586A4;">
                <div class="divTableHead">Modules</div>
                <div class="divTableHead">Date d’intervention</div>
                <div class="divTableHead">Intervenant</div>
                <div class="divTableHead">Objectifs pédagogiques</div>
                <div class="divTableHead">Positionnement</div>
            </div>
        </div>
        <div class="divTableBody">
            <#list interventions as gpd>
                <div class="divTableRow">
                    <div class="divTableCell">${gpd.module}</div>
                    <div class="divTableCell">Du ${gpd.dateDebut} Au ${gpd.dateFin} </div>
                    <div class="divTableCell">${gpd.formateur}</div>
                    <div class="divTableCell">${gpd.objectifPedagogiques}</div>
                    <div class="divTableCell">
                        <#list gpd.etudiantsPositionnements as etudiant, positionnement>
                            <section>
                                <div class="nom">${etudiant}</div>
                                <div class="divTable blueTable">
                                    <div class="divTableHeading">
                                        <div class="divTableRow">
                                            <div class="divTableHead niveau" style="background: #0586A4;">Debut</div>
                                            <div class="divTableHead niveau" style="background: #0586A4;">Fin</div>
                                        </div>
                                    </div>
                                    <div class="divTableBody">
                                        <div class="divTableRow">
                                            <div class="divTableCell niveau"
                                                style="background:#${positionnement.niveauDebut.codeCouleurHexa}">
                                                ${positionnement.niveauDebut.valeur}</div>
                                            <div class="divTableCell niveau"
                                                style="background:#${positionnement.niveauFin.codeCouleurHexa}">
                                                ${positionnement.niveauFin.valeur}</div>
                                        </div>
                                    </div>
                                </div>

                            </section>
                        </#list>
                    </div>
                </div>
            </#list>
        </div>
    </div>
    <#list niveaux as nv>
    <section class="container-legende">
    	<div>
    		<span class="span-value" style="background:#${nv.codeCouleurHexa}">
    		${nv.valeur}
    		</span> 
    		<span>
    		${nv.description}
    		</span> 
    	</div>
    </section>
     </#list>
</body>

</html>