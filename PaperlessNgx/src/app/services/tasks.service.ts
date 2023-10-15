import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import { first } from 'rxjs/operators'
import {
  PaperlessTask,
  PaperlessTaskStatus,
  PaperlessTaskType,
} from 'src/app/data/paperless-task'
import { environment } from 'src/environments/environment'

@Injectable({
  providedIn: 'root',
})
export class TasksService {
  private baseUrl: string = environment.apiBaseUrl

  loading: boolean

  private fileTasks: PaperlessTask[] = []

  public get total(): number {
    return this.fileTasks.length
  }

  public get allFileTasks(): PaperlessTask[] {
    return this.fileTasks.slice(0)
  }

  public get queuedFileTasks(): PaperlessTask[] {
    return this.fileTasks.filter((t) => t.status == PaperlessTaskStatus.Pending)
  }

  public get startedFileTasks(): PaperlessTask[] {
    return this.fileTasks.filter((t) => t.status == PaperlessTaskStatus.Started)
  }

  public get completedFileTasks(): PaperlessTask[] {
    return this.fileTasks.filter(
      (t) => t.status == PaperlessTaskStatus.Complete
    )
  }

  public get failedFileTasks(): PaperlessTask[] {
    return this.fileTasks.filter((t) => t.status == PaperlessTaskStatus.Failed)
  }

  constructor(private http: HttpClient) {}

  public reload() {
    this.loading = true

    this.http
      .get<PaperlessTask[]>(`${this.baseUrl}tasks/`)
      .pipe(first())
      .subscribe((r) => {
        this.fileTasks = r.filter((t) => t.type == PaperlessTaskType.File) // they're all File tasks, for now
        this.loading = false
      })
  }

  public dismissTasks(task_ids: Set<number>) {
    this.http
      .post(`${this.baseUrl}acknowledge_tasks/`, {
        tasks: [...task_ids],
      })
      .pipe(first())
      .subscribe((r) => {
        this.reload()
      })
  }
}
