const API = "/contas";

async function criarConta() {
    const numero = document.getElementById("numero").value;

    let conta = await fetch(API, {
	    method: "POST",
	    headers: { "Content-Type": "application/json" },
	    body: JSON.stringify({ numero: Number(numero) })
	});
	
	// Adicione o await e os parênteses ()
	const dados = await conta.json();

	document.querySelector('#lista-contas p').textContent = ' Numero: ' + dados.numero + ' - Saldo: ' + dados.saldo; 
	
}
 
async function buscarConta(){
	const numero = document.getElementById("buscar_numero").value;
	let endPoint = API + '/' + Number(numero);
	
	let buscarConta = await fetch(endPoint,{
		method: "GET",
		headers:  { "Content-Type": "application/json" }
	})

	const dados = await buscarConta.json();	
	document.querySelector('#lista-contas p').textContent = ' Numero: ' + dados.numero + ' - Saldo: ' + dados.saldo;

}

async function depositar(){
	const numero = document.getElementById("deposito_numero").value;
	const valor = document.getElementById("deposito_valor").value;
	let endPoint = API  + '/' + numero + '/depositar';
	
	let deposito = await fetch(endPoint,{
		method: "POST",
		headers: {"Content-Type": "application/json"},
		body: JSON.stringify({numero: Number(numero), valor: Number(valor)})
	})
	
	const dados = await deposito.json();
	console.log(dados)	
	document.querySelector('#lista-contas p').textContent = ' Numero: ' + dados.numero + ' - Saldo: ' + dados.saldo;

	
}
 
async function sacar(){
	const numero = document.getElementById("saque_numero").value;
	const valor = document.getElementById("saque_valor").value;
	let endPoint = API  + '/' + numero + '/sacar';
	
	let saque = await fetch(endPoint,{
		method: "POST",
		headers: {"Content-Type": "application/json"},
		body: JSON.stringify({numero: Number(numero), valor: Number(valor)})
	})
	
	const dados = await saque.json();
	console.log(dados)	
	document.querySelector('#lista-contas p').textContent = ' Numero: ' + dados.numero + ' - Saldo: ' + dados.saldo;

	
}


async function listarContas() {
    const response = await fetch(API,{
		method: "GET",
		headers: {"Content-Type": "application/json"}
	});
    
		
	const dados = await response.json();
	console.log(dados)	
			document.querySelector('#lista-contas p').textContent = JSON.stringify(dados, null, 2);
}

async function transferir() {
    const origem = document.getElementById("origem").value;
    const destino = document.getElementById("destino").value;
    const valor = document.getElementById("valor").value;

    await fetch(`${API}/transferencia`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            origem: Number(origem),
            destino: Number(destino),
            valor: Number(valor)
        })
    });

    listarContas();
}
