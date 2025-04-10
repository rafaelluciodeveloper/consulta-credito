import {AfterViewInit, Component, ViewChild} from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatSelectModule} from '@angular/material/select';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ApiService} from '../../services/api.service';
import {catchError, finalize, map, of, tap} from 'rxjs';
import {Credito} from '../../models/credito.model';
import {MatIcon} from '@angular/material/icon';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatBadgeModule} from '@angular/material/badge';
import {MatSort, MatSortModule} from '@angular/material/sort';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, MatSelectModule, MatTableModule, MatButtonModule, MatInputModule, MatProgressSpinnerModule, MatIcon, MatBadgeModule , MatSortModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements AfterViewInit {
  searchType = 'numeroNfse';
  searchValue = '';
  isLoading = false;

  dataSource = new MatTableDataSource<Credito>();

  @ViewChild(MatSort) sort!: MatSort;

  displayedColumns: string[] = [
    'numeroCredito',
    'numeroNfse',
    'dataConstituicao',
    'valorIssqn',
    'tipoCredito',
    'simplesNacional',
    'aliquota',
    'valorFaturado',
    'valorDeducao',
    'baseCalculo'
  ];

  constructor(private snackBar: MatSnackBar, private apiService: ApiService) {
  }

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  search() {
    if (!this.searchValue || this.searchValue.trim() === '') {
      this.snackBar.open('O campo "Valor" é obrigatório.', 'Fechar', {
        duration: 3000
      });
      return;
    }

    this.isLoading = true;

    const searchMethod = this.searchType === 'numeroNfse'
      ? this.apiService.searchByNumeroNfse(this.searchValue)
      : this.apiService.searchByNumeroCredito(this.searchValue);

    searchMethod.pipe(
      map((data: Credito[]) =>
        data.map((c: Credito) => ({
          ...c,
          dataConstituicao: new Date(c.dataConstituicao)
        }))
      ),
      tap(updatedData => {
        this.dataSource.data = updatedData;
      }),
      catchError(error => {
        console.error('Erro na busca:', error);
        this.snackBar.open('Erro ao consultar dados. Tente novamente.', 'Fechar', {
          duration: 4000,
          panelClass: ['snackbar-error']
        });
        this.dataSource.data = [];
        return of([]);
      }),
      finalize(() => this.isLoading = false)
    ).subscribe();
  }
}
