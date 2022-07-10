
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Bultin Eval</title>
    <style>
        body{
            display: flex;
            flex-flow: column nowrap;
            align-items:center;
        }
        .red{
            color: brown;
           
        }
        span{
            font-weight: bold;
        }
              div.greyGridTable {
            border: 2px solid #FFFFFF;
            width: 100%;
            text-align: center;
            border-collapse: collapse;
        }

        .divTable.greyGridTable .divTableCell,
        .divTable.greyGridTable .divTableHead {
            border: 2px solid #B4B4B4;
            padding: 3px 4px;
        }

        .divTable.greyGridTable .divTableBody .divTableCell {
            font-size: 17px;
        }

        .divTable.greyGridTable .divTableRow:nth-child(even) {
            background: #D0E4F5;
        }

        .divTable.greyGridTable .divTableHeading {
            background: #FFFFFF;
            border-bottom: 4px solid #333333;
        }

        .divTable.greyGridTable .divTableHeading .divTableHead {
            font-size: 17px;
            font-weight: bold;
            color: #333333;
            text-align: center;
            border-left: 2px solid #333333;
        }

        .divTable.greyGridTable .divTableHeading .divTableHead:first-child {
            border-left: none;
        }

        .greyGridTable .tableFootStyle {
            font-size: 14px;
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
           p{
            text-align: left;
        }
    </style>
</head>
<body>
   
    <h1 class="red"> Bulletin d’évaluation </h1>

    <h2> Titre Professionnel </h2>

    <h2 class="red">${titre}</h2>

    <section>
        <p> <span>Nom :</span>${lastName} </p>
        <p> <span>Prénom :</span>${firstName}</p>
        <p> <span>Année d’étude :${date}</span></p>
        <p> <span>Filière :</span></p>
    </section>

     <h3  class="red">CONTROLE CONTINU </h3>
     <section>
       <div class="divTable greyGridTable">
            <div class="divTableHeading">
                <div class="divTableRow">
                    <div class="divTableHead">Bloc</div>
                    <div class="divTableHead">Moyenne de l’étudiant </div>
                    <div class="divTableHead">Moyenne de la promotion </div>
                </div>
            </div>
               	<#assign x =0>
       <#list blocsComp as bc, cs >
       
	         <div class="divTableBody">
                <div class="divTableRow">
                    <div class="divTableCell">
	                	<p><span> ${x +1}- ${bc.titre}  </span></p>
	                <#list cs as c>
	                   <p>🗸 ${c.titre}</p>
	                </#list>
	                </div>
					   <div class="divTableCell">${moyenneEtudiant[x]}</div>
					   <div class="divTableCell">${moyennePromotion[x]}</div>

                </div>
            </div>
            <#assign x++>
	    </#list> 
	             
        </div>

     </section>

     <section>
        <p> <span>Moyenne générale de l’étudiant :</span> ${moyenneGeneraleEtudiant}</p>
        <p> <span>Moyenne générale de la promotion :</span>${moyenneGeneralePromotion}</p>
    </section>
</body>
</html>