export class ComplexValidators {
    static checkRelation(controlOne: string, controlTwo: string) {
        return (control: { get: (arg0: any) => { (): any; new(): any; value: any; }; }) => {
            let c1 = control.get(controlOne).value;
            let c2 = control.get(controlTwo).value;

            return (c1 == 'Old Washing Machine' && c2) ? { relationFail : true } : null; // return null if no error or return an error object
        };
    }
}