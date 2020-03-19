import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  public refundCustommer(id: string, amount: number): Observable<boolean> {

    const inputRefund =  `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" ` +
      `xmlns:tran="http://localhost:8080/api/TransactionUserService">` +
      `<soapenv:Header/>` +
      `<soapenv:Body>` +
      `<tran:refundCustomer><arg0><amount>${amount}</amount><id>${id}</id></arg0></tran:refundCustomer></soapenv:Body>` +
      `</soapenv:Envelope>`;

    return this.http.post('http://localhost:8080/api/UserTransactionService', inputRefund, { responseType: 'text' })
      .pipe(
        map((xmlString: string) => {
          const asJson = this.xmlStringToJson(xmlString);
          return asJson['soap:Envelope']['soap:Body']['ns2:refundCustomerResponse'].return['#text'];
        })
      );
  }

  public debitCustommer(id: string, amount: number): Observable<boolean> {

    const inputDebit =  `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" ` +
      `xmlns:tran="http://localhost:8080/api/TransactionUserService">` +
      `<soapenv:Header/>` +
      `<soapenv:Body>` +
      `<tran:debitCustomer><arg0><amount>${amount}</amount><id>${id}</id></arg0></tran:debitCustomer></soapenv:Body>` +
      `</soapenv:Envelope>`;

    return this.http.post('http://localhost:8080/api/UserTransactionService', inputDebit, { responseType: 'text' })
      .pipe(
        map((xmlString: string) => {
          const asJson = this.xmlStringToJson(xmlString);
          return asJson['soap:Envelope']['soap:Body']['ns2:debitCustomerResponse'].return['#text'];
        })
      );
  }

  private xmlStringToJson(xml: string): {} {
    // Convert the XML string to an XML Document.
    const oParser = new DOMParser();
    const oDOM = oParser.parseFromString(xml, 'application/xml');
    // Convert the XML Document to a JSON Object.
    return this.xmlToJson(oDOM);
  }

  private xmlToJson(xml): {} {
    // Create the return object
    let obj = {};

    if (xml.nodeType === 1) { // element
      // do attributes
      if (xml.attributes.length > 0) {
        obj['@attributes'] = {};
        for (let j = 0; j < xml.attributes.length; j++) {
          const attribute = xml.attributes.item(j);
          obj['@attributes'][attribute.nodeName] = attribute.nodeValue;
        }
      }
    } else if (xml.nodeType === 3) { // text
      obj = xml.nodeValue;
    }

    // do children
    if (xml.hasChildNodes()) {
      for (let i = 0; i < xml.childNodes.length; i++) {
        const item = xml.childNodes.item(i);
        const nodeName = item.nodeName;
        if (typeof(obj[nodeName]) === 'undefined') {
          obj[nodeName] = this.xmlToJson(item);
        } else {
          if (typeof(obj[nodeName].push) === 'undefined') {
            const old = obj[nodeName];
            obj[nodeName] = [];
            obj[nodeName].push(old);
          }
          obj[nodeName].push(this.xmlToJson(item));
        }
      }
    }
    return obj;
  }

}
